/*
 * $Id: Histogram.java,v 1.2 2006/12/08 13:48:37 marcop Exp $
 * Copyright (c) by Brockmann Consult 2003
 */

package org.esa.beam.util.math;

import com.bc.ceres.core.ProgressMonitor;
import com.bc.ceres.core.SubProgressMonitor;
import org.esa.beam.util.Debug;
import org.esa.beam.util.Guardian;

/**
 * Instances of the <code>Histogram</code> class store histogram data.
 *
 * @author Norman Fomferra
 */
public class Histogram extends Range {

    public static final float LEFT_AREA_SKIPPED_95 = 0.025F;
    public static final float RIGHT_AREA_SKIPPED_95 = 0.025F;

    private int[] binCounts;
    private int maxBinCount;
    private int binCountsSum;

    /**
     * Constructs a new instance for the given bin counts and the given value range.
     *
     * @param binCounts the bin counts
     * @param min       the minimum value
     * @param max       the maximum value
     */
    public Histogram(int[] binCounts, double min, double max) {
        setBinCounts(binCounts, min, max);
    }

    /**
     * Gets the number of bins in this histogram.
     *
     * @return the number of bins
     */
    public int getNumBins() {
        return binCounts.length;
    }

    /**
     * Gets the bin values of this histogram.
     *
     * @return the bin values
     */
    public int[] getBinCounts() {
        return binCounts;
    }

    /**
     * Sets the bin values and range of this histogram.
     *
     * @param binValues the bin values
     * @param min       the minimum value of the range
     * @param max       the maximum value of the range
     */
    public void setBinCounts(int[] binValues, double min, double max) {
        setBinCounts(binValues);
        setMin(min);
        setMax(max);
    }

    /**
     * Sets the bin values of this histogram.
     *
     * @param binCounts the bin values
     */
    public void setBinCounts(int[] binCounts) {
        this.binCounts = binCounts;
        updateBinCountSumAndBinCountMax();
    }

    /**
     * Gets the maximum bin value found this histogram.
     *
     * @return the maximum bin value
     */
    public int getMaxBinCount() {
        return maxBinCount;
    }

    /**
     * Gets the sum of all counts
     *
     * @return the sum of all counts.
     */
    public int getBinCountsSum() {
        return binCountsSum;
    }

    /**
     * Returns the value range for the case that 2.5% of the sum of all bin values are skipped from the the lower and
     * upper bounds of this histogram.
     *
     * @return the skipped value range, that include 95% of the sum of all bin values
     */
    public Range findRangeFor95Percent() {
        return findRange(LEFT_AREA_SKIPPED_95, RIGHT_AREA_SKIPPED_95);
    }

    /**
     * Returns the value range for the case that the given ratios of the sum of all bin values are skipped from the
     * lower and upper bounds of this histogram.
     *
     * @param leftHistoAreaSkipped  the normalized area (a ratio of the entire area) of samples skipped from the left
     *                              end of the histogram, must be greater than or equal to <code>0.0</code> and less
     *                              than <code>1.0 - rightHistoAreaSkipped</code>
     * @param rightHistoAreaSkipped the normalized area (a ratio of the entire area) of samples skipped from the upper
     *                              end of the histogram must be greater than or equal to <code>0.0</code> and less than
     *                              <code>1.0 - leftHistoAreaSkipped</code>
     * @return the readjusted range
     */
    public Range findRange(final double leftHistoAreaSkipped, final double rightHistoAreaSkipped) {
        return findRange(leftHistoAreaSkipped, rightHistoAreaSkipped, false, false);
    }

    public Range findRange(final double leftHistoAreaSkipped, final double rightHistoAreaSkipped, boolean skipLeftPeek, boolean skipRightPeek) {

        final int numBins = getNumBins();
        final int[] binCounts = getBinCounts();
        final int numSamplesTotal = getBinCountsSum();
        final int jMin = 0;
        final int jMax = numBins - 1;

        int j1 = 0;
        int j2 = jMax;
        int numSamples = numSamplesTotal;

        if (skipLeftPeek) {
            int jSkip = -1;
            for (int j = 1; j <= jMax; j++) {
                final int c0 = binCounts[j];
                final int c1 = binCounts[j - 1];
                final int c2 = j < 2 ? 0 : binCounts[j - 2];
                if (c1 > 0) {
                    if (c0 == 0 && c2 == 0) {
                        jSkip = j;
                    }
                    break;
                }
            }
            if (jSkip != -1) {
                j1 = jSkip;
                numSamples -= binCounts[jSkip];
            }
        }
        if (skipRightPeek) {
            int jSkip = -1;
            for (int j = jMax - 1; j >= 0; j--) {
                final int c0 = binCounts[j];
                final int c1 = binCounts[j + 1];
                final int c2 = j < jMax - 2 ? 0 : binCounts[j + 2];
                if (c1 > 0) {
                    if (c0 == 0 && c2 == 0) {
                        jSkip = j;
                    }
                    break;
                }
            }
            if (jSkip != -1) {
                j2 = jSkip;
                numSamples -= binCounts[jSkip];
            }
        }

        if (j1 >= j2 || numSamples <= 0) {
            j1 = jMin;
            j2 = jMax;
            numSamples = numSamplesTotal;
        }

        double leftArea, rightArea;
        double binCountSum = 0.0;
        for (; j1 <= jMax; j1++) {
            binCountSum += binCounts[j1];
            leftArea = binCountSum / numSamples;
            if (leftArea > leftHistoAreaSkipped) {
                break;
            }
        }
        if (j1 > jMax) {
            j1 = jMax;
        }

        binCountSum = 0;
        for (; j2 >= jMin; j2--) {
            binCountSum += binCounts[j2];
            rightArea = binCountSum / numSamples;
            if (rightArea > rightHistoAreaSkipped) {
                break;
            }
        }
        if (j2 < jMin) {
            j2 = jMin;
        }

        if (j1 > j2) {
            int temp = j1;
            j1 = j2;
            j2 = temp;
        } else if (j1 == j2) {
            if (j2 < jMax) {
                j2++;
            } else if (j1 > jMin) {
                j1--;
            }
        }

        final Range range = getRange(j1, j2);

        Debug.trace(
                "Histogram: lower bin index = " + j1 + " (less than " + (leftHistoAreaSkipped * 100.0F) + "% of pixels skipped)");
        Debug.trace(
                "Histogram: upper bin index = " + j2 + " (less than " + (rightHistoAreaSkipped * 100.0F) + "% of pixels skipped)");
        Debug.trace("Histogram: histo sample min = " + range.getMin() + "; sample max = " + range.getMax());

        return range;
    }

    /**
     * Gets the data value range for the given bin index.
     *
     * @param binIndex the bin index
     * @return the data value range
     */
    public Range getRange(int binIndex) {
        return getRange(binIndex, binIndex);
    }

    /**
     * Gets the data value range for the given bin index range.
     *
     * @param binIndex1 the first bin index
     * @param binIndex2 the second bin index
     * @return the data value range
     */
    public Range getRange(int binIndex1, int binIndex2) {
        return new Range(getMin() + binIndex1 * (getMax() - getMin()) / (double) getNumBins(),
                         getMin() + (binIndex2 + 1) * (getMax() - getMin()) / (double) getNumBins());
    }

    /**
     * Gets the bin index for the given value.
     *
     * @param value the value
     * @return the bin index or <code>-1</code> if the value is not covered by this histogram.
     */
    public int getBinIndex(double value) {
        if (value == getMin()) {
            return 0;
        }
        if (value == getMax()) {
            return getNumBins() - 1;
        }
        final int binIndex = MathUtils.floorInt((value - getMin()) / (getMax() - getMin()) * getNumBins());
        if (binIndex >= 0 && binIndex < getNumBins()) {
            return binIndex;
        }
        return -1;
    }

    @Override
    public void aggregate(final Object values, boolean unsigned,
                          final IndexValidator validator,
                          ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final Histogram histogram = computeHistogramGeneric(values, unsigned, validator,
                                                            getNumBins(), new Range(getMin(), getMax()),
                                                            null, pm);
        final int[] newCounts = histogram.getBinCounts();
        final int[] thisCounts = getBinCounts();
        for (int i = 0; i < thisCounts.length; i++) {
            thisCounts[i] += newCounts[i];
        }
        updateBinCountSumAndBinCountMax();
    }

    private void updateBinCountSumAndBinCountMax() {
        final int[] binCounts = getBinCounts();
        maxBinCount = Integer.MIN_VALUE;
        binCountsSum = 0;
        int binCount;
        if (binCounts != null) {
            for (int i = 0; i < binCounts.length; i++) {
                binCount = binCounts[i];
                if (maxBinCount < binCount) {
                    maxBinCount = binCount;
                }
                binCountsSum += binCount;
            }
        }
    }


    /**
     * @see #computeHistogramUByte
     * @deprecated in 4.0, use {@link #computeHistogramByte(byte[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramByte(final byte[] values,
                                                 final IndexValidator validator,
                                                 final int numBins,
                                                 Range range,
                                                 Histogram histo) {
        return computeHistogramByte(values, validator, numBins, range, histo, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>byte</code> array in the given value range. The array
     * elements are interpreted as <i>signed</i> byte values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array
     * @see #computeHistogramUByte
     */
    public static Histogram computeHistogramByte(final byte[] values,
                                                 final IndexValidator validator,
                                                 final int numBins,
                                                 Range range,
                                                 Histogram histo,
                                                 ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeByte(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final int min = MathUtils.floorInt(range.getMin());
            final int max = MathUtils.floorInt(range.getMax());
            final int delta = max > min ? max - min : 1;
            int value;
            int binIndex;

            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i];
                        if (value >= min && value <= max) {
                            binIndex = (numBins * (value - min)) / delta;
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histo != null) {
                histo.setBinCounts(binVals, min, max);
            } else {
                histo = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histo;
    }

    /**
     * @see #computeHistogramByte
     * @deprecated in 4.0, use {@link #computeHistogramUByte(byte[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramUByte(final byte[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histo) {
        return computeHistogramUByte(values, validator, numBins, range, histo, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>byte</code> array in the given value range. The array
     * elements are interpreted as <i>unsigned</i> byte values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array
     * @see #computeHistogramByte
     */
    public static Histogram computeHistogramUByte(final byte[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histo,
                                                  ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeUByte(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final int min = MathUtils.floorInt(range.getMin());
            final int max = MathUtils.floorInt(range.getMax());
            final int delta = max > min ? max - min : 1;
            int value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i] & 0xff;
                        if (value >= min && value <= max) {
                            binIndex = (numBins * (value - min)) / delta;
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histo != null) {
                histo.setBinCounts(binVals, min, max);
            } else {
                histo = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histo;
    }

    /**
     * @see #computeHistogramUShort
     * @deprecated in 4.0, use {@link #computeHistogramShort(short[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)}
     */
    public static Histogram computeHistogramShort(final short[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histo) {
        return computeHistogramShort(values, validator, numBins, range, histo, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>short</code> array in the given value range. The array
     * elements are interpreted as <i>signed</i> short values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @return the histogram for the given array
     * @see #computeHistogramUShort
     */
    public static Histogram computeHistogramShort(final short[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histo,
                                                  ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeShort(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final int min = MathUtils.floorInt(range.getMin());
            final int max = MathUtils.floorInt(range.getMax());
            final int delta = max > min ? max - min : 1;
            int value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i];
                        if (value >= min && value <= max) {
                            binIndex = (numBins * (value - min)) / delta;
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histo != null) {
                histo.setBinCounts(binVals, min, max);
            } else {
                histo = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histo;
    }

    /**
     * @see #computeHistogramShort
     * @deprecated in 4.0, use {@link #computeHistogramUShort(short[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramUShort(final short[] values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histo) {
        return computeHistogramUShort(values, validator, numBins, range, histo, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>short</code> array in the given value range. The array
     * elements are interpreted as <i>unsigned</i> short values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array
     * @see #computeHistogramShort
     */
    public static Histogram computeHistogramUShort(final short[] values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histo,
                                                   ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeUShort(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final int min = MathUtils.floorInt(range.getMin());
            final int max = MathUtils.floorInt(range.getMax());
            final int delta = max > min ? max - min : 1;
            int value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i] & 0xffff;
                        if (value >= min && value <= max) {
                            binIndex = (numBins * (value - min)) / delta;
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histo != null) {
                histo.setBinCounts(binVals, min, max);
            } else {
                histo = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histo;
    }

    /**
     * @see #computeHistogramUInt
     * @deprecated in 4.0, use {@link #computeHistogramInt(int[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramInt(final int[] values,
                                                final IndexValidator validator,
                                                final int numBins,
                                                Range range,
                                                Histogram histogram) {
        return computeHistogramInt(values, validator, numBins, range, histogram, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>int</code> array in the given value range. The array
     * elements are interpreted as <i>signed</i> byte values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param histogram a histogram instance to be reused, can be <code>null</code>
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array
     * @see #computeHistogramUInt
     */
    public static Histogram computeHistogramInt(final int[] values,
                                                final IndexValidator validator,
                                                final int numBins,
                                                Range range,
                                                Histogram histogram,
                                                ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeInt(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final long min = MathUtils.floorLong(range.getMin());
            final long max = MathUtils.floorLong(range.getMax());
            final long delta = max > min ? max - min : 1;
            final double scale = numBins / (double) delta;
            final double offset = -scale * min;
            int value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i];
                        if (value >= min && value <= max) {
                            binIndex = (int) (scale * value + offset);
                            //                        binIndex = (numBins * (value - min)) / delta;
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histogram != null) {
                histogram.setBinCounts(binVals, min, max);
            } else {
                histogram = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histogram;
    }

    /**
     * @see #computeHistogramInt
     * @deprecated in 4.0, use {@link #computeHistogramUInt(int[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramUInt(final int[] values,
                                                 final IndexValidator validator,
                                                 final int numBins,
                                                 Range range,
                                                 Histogram histogram) {
        return computeHistogramUInt(values, validator, numBins, range, histogram, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>int</code> array in the given value range. The array
     * elements are interpreted as <i>unsigned</i> byte values. Values at a given index <code>i</code> for which
     * <code>validator.validate(i)</code> returns <code>false</code> are excluded from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param histogram a histogram instance to be reused, can be <code>null</code>
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array of values
     * @see #computeHistogramInt
     */
    public static Histogram computeHistogramUInt(final int[] values,
                                                 final IndexValidator validator,
                                                 final int numBins,
                                                 Range range,
                                                 Histogram histogram,
                                                 ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeUInt(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final long min = MathUtils.floorLong(range.getMin());
            final long max = MathUtils.floorLong(range.getMax());
            final long delta = max > min ? max - min : 1;
            long value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i] & 0xffffffffL;
                        if (value >= min && value <= max) {
                            binIndex = (int) ((numBins * (value - min)) / delta);
                            if (binIndex == numBins) {
                                binIndex = numBins - 1;
                            }
                            binVals[binIndex]++;
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histogram != null) {
                histogram.setBinCounts(binVals, min, max);
            } else {
                histogram = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histogram;
    }

    /**
     * @see #computeHistogramDouble
     * @deprecated in 4.0, use {@link #computeHistogramFloat(float[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramFloat(final float[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histogram) {
        return computeHistogramFloat(values, validator, numBins, range, histogram, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>float</code> array in the given value range. Values at a
     * given index <code>i</code> for which <code>validator.validate(i)</code> returns <code>false</code> are excluded
     * from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param histogram a histogram instance to be reused, can be <code>null</code>
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array of values
     * @see #computeHistogramDouble
     */
    public static Histogram computeHistogramFloat(final float[] values,
                                                  final IndexValidator validator,
                                                  final int numBins,
                                                  Range range,
                                                  Histogram histogram,
                                                  ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeFloat(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final float min = (float) range.getMin();
            final float max = (float) range.getMax();
            final float delta = max > min ? max - min : 1;
            final float scale = numBins / delta;
            final float offset = -scale * min;
            float value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i];
                        if (!Float.isNaN(value) && !Float.isInfinite(value)) {
                            if (value >= min && value <= max) {
                                binIndex = (int) (scale * value + offset);
                                if (binIndex == numBins) {
                                    binIndex = numBins - 1;
                                }
                                binVals[binIndex]++;
                            }
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histogram != null) {
                histogram.setBinCounts(binVals, min, max);
            } else {
                histogram = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histogram;
    }

    /**
     * @see #computeHistogramFloat
     * @deprecated in 4.0, use {@link #computeHistogramDouble(double[], IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramDouble(final double[] values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histogram) {
        return computeHistogramDouble(values, validator, numBins, range, histogram, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>double</code> array in the given value range. Values at
     * a given index <code>i</code> for which <code>validator.validate(i)</code> returns <code>false</code> are excluded
     * from the computation.
     *
     * @param values    the array whose histogram to compute
     * @param validator used to validate the array indexes, must not be <code>null</code>. Use {@link
     *                  IndexValidator#TRUE} instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param histogram a histogram instance to be reused, can be <code>null</code>
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given array of values
     * @see #computeHistogramFloat
     */
    public static Histogram computeHistogramDouble(final double[] values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histogram,
                                                   ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.length;
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeDouble(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final double min = range.getMin();
            final double max = range.getMax();
            final double delta = max > min ? max - min : 1;
            final double scale = numBins / delta;
            final double offset = -scale * min;
            double value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values[i];
                        if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                            if (value >= min && value <= max) {
                                binIndex = (int) (scale * value + offset);
                                if (binIndex == numBins) {
                                    binIndex = numBins - 1;
                                }
                                binVals[binIndex]++;
                            }
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histogram != null) {
                histogram.setBinCounts(binVals, min, max);
            } else {
                histogram = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histogram;
    }

    /**
     * @see #computeHistogramByte
     * @deprecated in 4.0, use {@link #computeHistogramDouble(DoubleList, IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)}
     */
    public static Histogram computeHistogramDouble(final DoubleList values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histo) {
        return computeHistogramDouble(values, validator, numBins, range, histo, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>Histogram.DoubleList</code> in the given value range.
     * Values at a given index <code>i</code> for which <code>validator.validate(i)</code> returns <code>false</code>
     * are excluded from the computation.
     *
     * @param values    the <code>Histogram.DoubleList</code> whose histogram to compute
     * @param validator used to validate the indexes, must not be <code>null</code>. Use {@link IndexValidator#TRUE}
     *                  instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given values
     * @see #computeHistogramByte
     */
    public static Histogram computeHistogramDouble(final DoubleList values,
                                                   final IndexValidator validator,
                                                   final int numBins,
                                                   Range range,
                                                   Histogram histo,
                                                   ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        final int numValues = values.getSize();
        final int[] binVals = new int[numBins];
        pm.beginTask("Computing histogram", range == null ? 2 : 1);
        try {
            if (range == null) {
                range = computeRangeDouble(values, validator, range, SubProgressMonitor.create(pm, 1));
            }
            final double min = range.getMin();
            final double max = range.getMax();
            final double delta = max - min;
            double value;
            int binIndex;
            ProgressMonitor subPm = SubProgressMonitor.create(pm, 1);
            subPm.beginTask("Computing histogram", numValues);
            try {
                for (int i = 0; i < numValues; i++) {
                    if (validator.validateIndex(i)) {
                        value = values.getDouble(i);
                        if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                            if (value >= min && value <= max) {
                                binIndex = (int) ((numBins * (value - min)) / delta);
                                if (binIndex == numBins) {
                                    binIndex = numBins - 1;
                                }
                                binVals[binIndex]++;
                            }
                        }
                    }
                    subPm.worked(1);
                }
            } finally {
                subPm.done();
            }
            if (histo != null) {
                histo.setBinCounts(binVals, min, max);
            } else {
                histo = new Histogram(binVals, min, max);
            }
        } finally {
            pm.done();
        }
        return histo;
    }


    /**
     * @see #computeHistogramByte
     * @deprecated in 4.0, use {@link #computeHistogramGeneric(Object, boolean, IndexValidator, int, Range, Histogram, com.bc.ceres.core.ProgressMonitor)} instead
     */
    public static Histogram computeHistogramGeneric(final Object values,
                                                    final boolean unsigned,
                                                    final IndexValidator validator,
                                                    int numBins, Range range, Histogram histogram) {
        return computeHistogramGeneric(values, unsigned, validator, numBins, range, histogram, ProgressMonitor.NULL);
    }

    /**
     * Computes the histogram for the values in the given <code>Histogram.DoubleList</code> in the given value range.
     * Values at a given index <code>i</code> for which <code>validator.validate(i)</code> returns <code>false</code>
     * are excluded from the computation.
     *
     * @param values    the <code>Histogram.DoubleList</code> whose histogram to compute
     * @param validator used to validate the indexes, must not be <code>null</code>. Use {@link IndexValidator#TRUE}
     *                  instead.
     * @param numBins   the number of bins for the histogram
     * @param range     the value range, if <code>null</code> the range is automatically computed
     * @param pm        a monitor to inform the user about progress
     * @return the histogram for the given values
     * @see #computeHistogramByte
     */
    public static Histogram computeHistogramGeneric(final Object values,
                                                    final boolean unsigned,
                                                    final IndexValidator validator,
                                                    int numBins, Range range, Histogram histogram,
                                                    ProgressMonitor pm) {
        Guardian.assertNotNull("validator", validator);
        if (values instanceof byte[]) {
            if (unsigned) {
                histogram = computeHistogramUByte((byte[]) values, validator, numBins, range, histogram, pm);
            } else {
                histogram = computeHistogramByte((byte[]) values, validator, numBins, range, histogram, pm);
            }
        } else if (values instanceof short[]) {
            if (unsigned) {
                histogram = computeHistogramUShort((short[]) values, validator, numBins, range, histogram, pm);
            } else {
                histogram = computeHistogramShort((short[]) values, validator, numBins, range, histogram, pm);
            }
        } else if (values instanceof int[]) {
            if (unsigned) {
                histogram = computeHistogramUInt((int[]) values, validator, numBins, range, histogram, pm);
            } else {
                histogram = computeHistogramInt((int[]) values, validator, numBins, range, histogram, pm);
            }
        } else if (values instanceof float[]) {
            histogram = computeHistogramFloat((float[]) values, validator, numBins, range, histogram, pm);
        } else if (values instanceof double[]) {
            histogram = computeHistogramDouble((double[]) values, validator, numBins, range, histogram, pm);
        } else if (values instanceof DoubleList) {
            histogram = computeHistogramDouble((DoubleList) values, validator, numBins, range, histogram, pm);
        } else if (values == null) {
            throw new IllegalArgumentException("values is null");
        } else {
            throw new IllegalArgumentException("values has an illegal type: " + values.getClass());
        }
        return histogram;
    }


    /**
     * @deprecated since BEAM 4.2, use {@link #findRangeFor95Percent()} instead.
     */
    @Deprecated
    public Range findRangeFor95Percent(final boolean skipInvalidZero) {
        return findRange(LEFT_AREA_SKIPPED_95, RIGHT_AREA_SKIPPED_95, skipInvalidZero, false);
    }

//    /**
//     * @deprecated since BEAM 4.2, use {@link #findRange(double, double)} instead.
//     */
//    @Deprecated
//    public Range findRange(final double leftHistoAreaSkipped,
//                           final double rightHistoAreaSkipped,
//                           final boolean skipInvalidZero) {
//         return findRange(leftHistoAreaSkipped, rightHistoAreaSkipped);
//    }
}
