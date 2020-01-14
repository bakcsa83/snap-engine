package org.esa.snap.core.dataio.geocoding;

public class S3_SYN {

    // ----------------------------------------------------
    // subset of SLSTR/OL synergy geolocation. pixel based geolocation.
    // DEM corrected raster points
    // raster size 32 x 26
    // ground resolution 300m x 300m
    public static final double[] SLSTR_OL_LAT = {
            45.855048, 45.854436, 45.853823999999996, 45.853823999999996, 45.853213, 45.852601, 45.85199, 45.851378, 45.850767, 45.850767, 45.850156, 45.849543999999995, 45.848932999999995, 45.848321999999996, 45.847712, 45.847712, 45.8471, 45.846489999999996, 45.84588, 45.845268, 45.844657999999995, 45.844657999999995, 45.844049, 45.843436999999994, 45.842828, 45.842217, 45.841608, 45.841608, 45.840996999999994, 45.840388, 45.839777, 45.839166999999996,
            45.852472999999996, 45.851861, 45.85125, 45.85125, 45.850638, 45.850027, 45.849415, 45.848804, 45.848192999999995, 45.848192999999995, 45.847581999999996, 45.846969, 45.846359, 45.845748, 45.845138, 45.845138, 45.844525, 45.843914999999996, 45.843305, 45.842693999999995, 45.842084, 45.842084, 45.841474, 45.840863, 45.840253, 45.839642, 45.839033, 45.839033, 45.838422, 45.837813999999995, 45.837202999999995, 45.836593,
            45.849899, 45.849287, 45.848675, 45.848675, 45.848064, 45.847452, 45.846841, 45.84623, 45.845617999999995, 45.845617999999995, 45.845008, 45.844395, 45.843784, 45.843174, 45.842563, 45.842563, 45.841950999999995, 45.841341, 45.840731, 45.840119, 45.839509, 45.839509, 45.838899999999995, 45.838288, 45.837679, 45.837067999999995, 45.836459, 45.836459, 45.835848, 45.835239, 45.834629, 45.834018,
            45.847324, 45.846712, 45.846101, 45.846101, 45.845489, 45.844878, 45.844266, 45.843655, 45.843044, 45.843044, 45.842433, 45.84182, 45.84121, 45.840599, 45.839988999999996, 45.839988999999996, 45.839377, 45.838767, 45.838156999999995, 45.837545, 45.836935, 45.836935, 45.836326, 45.835713999999996, 45.835105, 45.834494, 45.833884999999995, 45.833884999999995, 45.833273999999996, 45.832665, 45.832055, 45.831444,
            45.84475, 45.844138, 45.843526, 45.843526, 45.842915, 45.842303, 45.841691999999995, 45.841080999999996, 45.840469999999996, 45.840469999999996, 45.839859, 45.839245999999996, 45.838635, 45.838024999999995, 45.837413999999995, 45.837413999999995, 45.836802, 45.836192, 45.835581999999995, 45.83497, 45.834361, 45.834361, 45.833751, 45.83314, 45.832530999999996, 45.831919, 45.831309999999995, 45.831309999999995, 45.8307, 45.830090999999996, 45.82948, 45.828869999999995,
            45.842172999999995, 45.841561, 45.84095, 45.84095, 45.840337999999996, 45.839726999999996, 45.839115, 45.838504, 45.837893, 45.837893, 45.837281999999995, 45.836669, 45.836059, 45.835448, 45.834838, 45.834838, 45.834226, 45.833616, 45.833006, 45.832394, 45.831784, 45.831784, 45.831174999999995, 45.830563, 45.829954, 45.829342999999994, 45.828734, 45.828734, 45.828123, 45.827514, 45.826904, 45.826293,
            45.839599, 45.838986999999996, 45.838375, 45.838375, 45.837764, 45.837151999999996, 45.836541, 45.83593, 45.835319, 45.835319, 45.834708, 45.834095, 45.833484, 45.832874, 45.832263999999995, 45.832263999999995, 45.831651, 45.831041, 45.830431, 45.82982, 45.829209999999996, 45.829209999999996, 45.828601, 45.827988999999995, 45.82738, 45.826769, 45.826159999999994, 45.826159999999994, 45.825548999999995, 45.82494, 45.824329999999996, 45.823719,
            45.837024, 45.836411999999996, 45.835801, 45.835801, 45.835189, 45.834578, 45.833966, 45.833355, 45.832744, 45.832744, 45.832133, 45.831520999999995, 45.830909999999996, 45.8303, 45.829688999999995, 45.829688999999995, 45.829077, 45.828466999999996, 45.827856999999995, 45.827245, 45.826636, 45.826636, 45.826026, 45.825415, 45.824805999999995, 45.824194, 45.823585, 45.823585, 45.822975, 45.822365999999995, 45.821754999999996, 45.821145,
            45.83445, 45.833838, 45.833225999999996, 45.833225999999996, 45.832615, 45.832003, 45.831392, 45.830780999999995, 45.830169999999995, 45.830169999999995, 45.829558999999996, 45.828945999999995, 45.828336, 45.827725, 45.827115, 45.827115, 45.826502999999995, 45.825893, 45.825283, 45.824670999999995, 45.824061, 45.824061, 45.823451999999996, 45.822841, 45.822230999999995, 45.821619999999996, 45.821011, 45.821011, 45.8204, 45.819792, 45.819181, 45.818571,
            45.831875, 45.831264, 45.830652, 45.830652, 45.83004, 45.829429, 45.828818, 45.828207, 45.827596, 45.827596, 45.826985, 45.826372, 45.825761, 45.825151, 45.824540999999996, 45.824540999999996, 45.823927999999995, 45.823318, 45.822708999999996, 45.822097, 45.821487, 45.821487, 45.820878, 45.820266, 45.819657, 45.819046, 45.818436999999996, 45.818436999999996, 45.817826, 45.817218, 45.816607, 45.815996999999996,
            45.829301, 45.828689, 45.828077, 45.828077, 45.827466, 45.826854999999995, 45.826243, 45.825632, 45.825021, 45.825021, 45.82441, 45.823798, 45.823187, 45.822576999999995, 45.821965999999996, 45.821965999999996, 45.821354, 45.820744, 45.820133999999996, 45.819522, 45.818912999999995, 45.818912999999995, 45.818303, 45.817692, 45.817083, 45.816472, 45.815863, 45.815863, 45.815252, 45.814643, 45.814032999999995, 45.813421999999996,
            45.826727, 45.826114999999994, 45.825503, 45.825503, 45.824892, 45.824279999999995, 45.823668999999995, 45.823057999999996, 45.822447, 45.822447, 45.821836, 45.821222999999996, 45.820612999999994, 45.820001999999995, 45.819392, 45.819392, 45.81878, 45.818169999999995, 45.81756, 45.816948, 45.816339, 45.816339, 45.815729, 45.815118, 45.814508, 45.813897, 45.813288, 45.813288, 45.812678, 45.812069, 45.811457999999995, 45.810848,
            45.824152, 45.82354, 45.822928999999995, 45.822928999999995, 45.822317, 45.821706, 45.821093999999995, 45.820482999999996, 45.819872, 45.819872, 45.819261, 45.818649, 45.818038, 45.817428, 45.816818, 45.816818, 45.816205, 45.815594999999995, 45.814986, 45.814374, 45.813764, 45.813764, 45.813154999999995, 45.812543, 45.811934, 45.811322999999994, 45.810714, 45.810714, 45.810103, 45.809495, 45.808884, 45.808274,
            45.821577999999995, 45.820966, 45.820353999999995, 45.820353999999995, 45.819742999999995, 45.819131, 45.81852, 45.817909, 45.817298, 45.817298, 45.816686999999995, 45.816074, 45.815464, 45.814853, 45.814243, 45.814243, 45.813631, 45.813021, 45.812411, 45.811799, 45.811189999999996, 45.811189999999996, 45.810579999999995, 45.809968999999995, 45.80936, 45.808749, 45.808139999999995, 45.808139999999995, 45.807528999999995, 45.80692, 45.806309999999996, 45.805699999999995,
            45.819002999999995, 45.818391, 45.81778, 45.81778, 45.817167999999995, 45.816556999999996, 45.815946, 45.815334, 45.814723, 45.814723, 45.814113, 45.8135, 45.812889, 45.812279, 45.811668999999995, 45.811668999999995, 45.811057, 45.810446999999996, 45.809836999999995, 45.809225, 45.808616, 45.808616, 45.808006, 45.807395, 45.806785999999995, 45.806174999999996, 45.805566, 45.805566, 45.804955, 45.804345999999995, 45.803736, 45.803125,
            45.816429, 45.815816999999996, 45.815205, 45.815205, 45.814594, 45.813981999999996, 45.813371, 45.81276, 45.812149, 45.812149, 45.811538, 45.810925999999995, 45.810314999999996, 45.809705, 45.809093999999995, 45.809093999999995, 45.808482, 45.807871999999996, 45.807262, 45.806650999999995, 45.806041, 45.806041, 45.805431999999996, 45.80482, 45.804210999999995, 45.803599999999996, 45.802991, 45.802991, 45.802381, 45.801772, 45.801161, 45.800551,
            45.813854, 45.813241999999995, 45.812630999999996, 45.812630999999996, 45.812019, 45.811408, 45.810797, 45.810185999999995, 45.809574999999995, 45.809574999999995, 45.808963999999996, 45.808350999999995, 45.807741, 45.80713, 45.80652, 45.80652, 45.805907999999995, 45.805298, 45.804688, 45.804075999999995, 45.803467, 45.803467, 45.802856999999996, 45.802246, 45.801637, 45.801026, 45.800416999999996, 45.800416999999996, 45.799806, 45.799198, 45.798587, 45.797976999999996,
            45.81128, 45.810668, 45.810055999999996, 45.810055999999996, 45.809445, 45.808833, 45.808222, 45.807611, 45.806999999999995, 45.806999999999995, 45.806388999999996, 45.805777, 45.805166, 45.804556, 45.803945999999996, 45.803945999999996, 45.803334, 45.802724, 45.802113999999996, 45.801502, 45.800892999999995, 45.800892999999995, 45.800283, 45.799672, 45.799063, 45.798452, 45.797843, 45.797843, 45.797232, 45.796623, 45.796012999999995, 45.795403,
            45.808704999999996, 45.808093, 45.807482, 45.807482, 45.806869999999996, 45.806259, 45.805648, 45.805037, 45.804426, 45.804426, 45.803815, 45.803202, 45.802592, 45.801981, 45.801370999999996, 45.801370999999996, 45.800759, 45.800149, 45.799538999999996, 45.798928, 45.798317999999995, 45.798317999999995, 45.797709, 45.797097, 45.796488, 45.795877, 45.795268, 45.795268, 45.794658, 45.794049, 45.793439, 45.792828,
            45.806131, 45.805519, 45.804907, 45.804907, 45.804296, 45.803684, 45.803073, 45.802462, 45.801851, 45.801851, 45.801241, 45.800627999999996, 45.800017, 45.799406999999995, 45.798797, 45.798797, 45.798185, 45.797574999999995, 45.796965, 45.796352999999996, 45.795744, 45.795744, 45.795134, 45.794523, 45.793914, 45.793302999999995, 45.792694, 45.792694, 45.792083, 45.791475, 45.790864, 45.790254,
            45.803556, 45.802944, 45.802333, 45.802333, 45.801721, 45.80111, 45.800498999999995, 45.799887999999996, 45.799277, 45.799277, 45.798666, 45.798054, 45.797443, 45.796833, 45.796222, 45.796222, 45.795609999999996, 45.794999999999995, 45.794391, 45.793779, 45.793169, 45.793169, 45.792559999999995, 45.791948999999995, 45.79134, 45.790729, 45.790119999999995, 45.790119999999995, 45.789508999999995, 45.788900999999996, 45.788289999999996, 45.787679999999995,
            45.800982, 45.80037, 45.799758, 45.799758, 45.799147, 45.798536, 45.797923999999995, 45.797312999999995, 45.796701999999996, 45.796701999999996, 45.796091999999994, 45.795479, 45.794869, 45.794258, 45.793648, 45.793648, 45.793036, 45.792426, 45.791816, 45.791205, 45.790594999999996, 45.790594999999996, 45.789986, 45.789373999999995, 45.788765, 45.788154, 45.787546, 45.787546, 45.786935, 45.786325999999995, 45.785716, 45.785106,
            45.798407, 45.797795, 45.797184, 45.797184, 45.796572, 45.795961, 45.79535, 45.794739, 45.794128, 45.794128, 45.793517, 45.792905, 45.792294, 45.791684, 45.791073999999995, 45.791073999999995, 45.790462, 45.789851999999996, 45.789241999999994, 45.78863, 45.788021, 45.788021, 45.787411, 45.7868, 45.786190999999995, 45.785579999999996, 45.784971, 45.784971, 45.784361, 45.783752, 45.783141, 45.782531,
            45.795832999999995, 45.795221, 45.794609, 45.794609, 45.793997999999995, 45.793386999999996, 45.792775, 45.792164, 45.791554, 45.791554, 45.790943, 45.79033, 45.789719999999996, 45.789108999999996, 45.788498999999995, 45.788498999999995, 45.787887, 45.787276999999996, 45.786668, 45.786055999999995, 45.785446, 45.785446, 45.784836999999996, 45.784226, 45.783617, 45.783006, 45.782396999999996, 45.782396999999996, 45.781786, 45.781178, 45.780567, 45.779956999999996,
            45.793257999999994, 45.792646, 45.792035, 45.792035, 45.791422999999995, 45.790811999999995, 45.790200999999996, 45.78959, 45.788979, 45.788979, 45.788368, 45.787755999999995, 45.787144999999995, 45.786535, 45.785925, 45.785925, 45.785312999999995, 45.784703, 45.784093, 45.783482, 45.782872, 45.782872, 45.782263, 45.781651, 45.781042, 45.780431, 45.779823, 45.779823, 45.779212, 45.778603, 45.777992999999995, 45.777383,
            45.790684, 45.790071999999995, 45.78946, 45.78946, 45.788849, 45.788238, 45.787625999999996, 45.787015, 45.786404999999995, 45.786404999999995, 45.785793999999996, 45.785181, 45.784571, 45.783961, 45.78335, 45.78335, 45.782737999999995, 45.782128, 45.781518999999996, 45.780907, 45.780297999999995, 45.780297999999995, 45.779688, 45.779077, 45.778468, 45.777857, 45.777248, 45.777248, 45.776638, 45.776029, 45.775419, 45.774808
    };

    public static final double[] SLSTR_OL_LON = {
            -130.350693, -130.346619, -130.342546, -130.342546, -130.338475, -130.334406, -130.33033899999998, -130.32627499999998, -130.322212, -130.322212, -130.318151, -130.314079, -130.310021, -130.30596599999998, -130.30191299999998, -130.30191299999998, -130.29784899999999, -130.2938, -130.289752, -130.285694, -130.28164999999998, -130.28164999999998, -130.27760899999998, -130.27355599999999, -130.269519, -130.26547, -130.261436, -130.261436, -130.25739099999998, -130.25336099999998, -130.24931999999998, -130.245281,
            -130.351481, -130.347406, -130.343334, -130.343334, -130.339263, -130.335195, -130.331128, -130.32706299999998, -130.323, -130.323, -130.318939, -130.314868, -130.310811, -130.306756, -130.302703, -130.302703, -130.29863899999998, -130.29459, -130.29054299999999, -130.286484, -130.282441, -130.282441, -130.2784, -130.274347, -130.27031, -130.266261, -130.262227, -130.262227, -130.258183, -130.254153, -130.250112, -130.246073,
            -130.35226799999998, -130.348194, -130.344121, -130.344121, -130.340051, -130.335983, -130.331916, -130.32785099999998, -130.323789, -130.323789, -130.319728, -130.315657, -130.3116, -130.307545, -130.303492, -130.303492, -130.299429, -130.29538, -130.29133299999998, -130.287275, -130.283232, -130.283232, -130.27919, -130.275138, -130.271101, -130.267052, -130.26301899999999, -130.26301899999999, -130.258975, -130.254945, -130.250904, -130.24686499999999,
            -130.35305499999998, -130.34898099999998, -130.344909, -130.344909, -130.340839, -130.336771, -130.332704, -130.32864, -130.324578, -130.324578, -130.320517, -130.31644599999998, -130.312389, -130.308335, -130.304282, -130.304282, -130.300218, -130.29617, -130.292123, -130.288065, -130.284022, -130.284022, -130.279981, -130.275929, -130.271892, -130.267844, -130.26381, -130.26381, -130.25976599999998, -130.25573699999998, -130.25169599999998, -130.247658,
            -130.35384299999998, -130.34976899999998, -130.345697, -130.345697, -130.341627, -130.337559, -130.333493, -130.329428, -130.325366, -130.325366, -130.321306, -130.31723499999998, -130.313178, -130.309124, -130.305071, -130.305071, -130.301008, -130.296959, -130.292913, -130.28885499999998, -130.28481299999999, -130.28481299999999, -130.28077199999998, -130.27671999999998, -130.272683, -130.268635, -130.264602, -130.264602, -130.260558, -130.256528, -130.252488, -130.24845,
            -130.35463099999998, -130.35055699999998, -130.346485, -130.346485, -130.342415, -130.338347, -130.334281, -130.330217, -130.326155, -130.326155, -130.322095, -130.318024, -130.313968, -130.309914, -130.305862, -130.305862, -130.301798, -130.29775, -130.293704, -130.289646, -130.285604, -130.285604, -130.281563, -130.277511, -130.273475, -130.269427, -130.265394, -130.265394, -130.26135, -130.257321, -130.25328, -130.24924199999998,
            -130.355418, -130.35134499999998, -130.347273, -130.347273, -130.343203, -130.339136, -130.33507, -130.331006, -130.326944, -130.326944, -130.322884, -130.318814, -130.31475799999998, -130.310703, -130.306651, -130.306651, -130.302588, -130.29854, -130.294494, -130.290437, -130.286394, -130.286394, -130.282354, -130.278302, -130.27426599999998, -130.270218, -130.266185, -130.266185, -130.26214199999998, -130.25811299999998, -130.254073, -130.250035,
            -130.356206, -130.35213299999998, -130.348061, -130.348061, -130.343992, -130.339924, -130.335859, -130.331795, -130.327733, -130.327733, -130.32367399999998, -130.319603, -130.31554699999998, -130.31149299999998, -130.30744199999998, -130.30744199999998, -130.303379, -130.299331, -130.295285, -130.291228, -130.287185, -130.287185, -130.283145, -130.279094, -130.275058, -130.27101, -130.266977, -130.266977, -130.262934, -130.258905, -130.254865, -130.25082799999998,
            -130.356994, -130.35292099999998, -130.348849, -130.348849, -130.34478, -130.340713, -130.336648, -130.332584, -130.328523, -130.328523, -130.32446299999998, -130.320393, -130.316337, -130.312283, -130.308232, -130.308232, -130.304169, -130.300121, -130.296075, -130.29201899999998, -130.28797699999998, -130.28797699999998, -130.28393599999998, -130.279885, -130.275849, -130.271802, -130.267769, -130.267769, -130.263726, -130.259698, -130.25565799999998, -130.25162,
            -130.357782, -130.35370899999998, -130.349638, -130.349638, -130.34556899999998, -130.341502, -130.337436, -130.333373, -130.329312, -130.329312, -130.325252, -130.321182, -130.317127, -130.313073, -130.309022, -130.309022, -130.304959, -130.30091199999998, -130.296866, -130.292809, -130.288767, -130.288767, -130.284728, -130.280676, -130.27664099999998, -130.272594, -130.268561, -130.268561, -130.26451799999998, -130.26049, -130.25645, -130.252413,
            -130.35857, -130.35449699999998, -130.350426, -130.350426, -130.34635699999998, -130.34229, -130.338225, -130.334162, -130.33010099999998, -130.33010099999998, -130.326042, -130.321972, -130.317916, -130.313863, -130.309812, -130.309812, -130.305749, -130.301702, -130.297656, -130.2936, -130.289558, -130.289558, -130.285519, -130.281468, -130.277432, -130.273385, -130.269353, -130.269353, -130.26531, -130.261282, -130.257243, -130.253206,
            -130.359357, -130.35528499999998, -130.351214, -130.351214, -130.34714499999998, -130.343079, -130.339014, -130.334951, -130.33088999999998, -130.33088999999998, -130.326831, -130.32276099999999, -130.318706, -130.314653, -130.310602, -130.310602, -130.30653999999998, -130.302492, -130.29844699999998, -130.294391, -130.290349, -130.290349, -130.28631, -130.28225899999998, -130.278224, -130.27417699999998, -130.27014499999999, -130.27014499999999, -130.266102, -130.26207399999998, -130.258035, -130.253998,
            -130.360145, -130.35607199999998, -130.352002, -130.352002, -130.34793399999998, -130.343867, -130.339802, -130.335739, -130.331679, -130.331679, -130.32762, -130.32354999999998, -130.319495, -130.315443, -130.31139199999998, -130.31139199999998, -130.30733, -130.303283, -130.299237, -130.29518099999999, -130.29113999999998, -130.29113999999998, -130.287101, -130.28305, -130.279015, -130.274968, -130.270937, -130.270937, -130.266894, -130.262866, -130.258827, -130.25479099999998,
            -130.360933, -130.35685999999998, -130.35279, -130.35279, -130.34872199999998, -130.344655, -130.340591, -130.336528, -130.332468, -130.332468, -130.328409, -130.32434, -130.32028499999998, -130.31623199999999, -130.31218099999998, -130.31218099999998, -130.30812, -130.304073, -130.300028, -130.295972, -130.291931, -130.291931, -130.287892, -130.283841, -130.279807, -130.27576, -130.271729, -130.271729, -130.267686, -130.263659, -130.25961999999998, -130.255583,
            -130.36172, -130.35764799999998, -130.353578, -130.353578, -130.34950999999998, -130.345444, -130.341379, -130.33731699999998, -130.333257, -130.333257, -130.329198, -130.325129, -130.32107399999998, -130.31702199999998, -130.312971, -130.312971, -130.30891, -130.30486299999998, -130.300818, -130.296763, -130.292722, -130.292722, -130.288683, -130.28463299999999, -130.280598, -130.27655099999998, -130.27252, -130.27252, -130.268478, -130.264451, -130.260412, -130.256376,
            -130.362508, -130.35843599999998, -130.354366, -130.354366, -130.35029799999998, -130.346232, -130.342168, -130.33810599999998, -130.334045, -130.334045, -130.329987, -130.325918, -130.321864, -130.317811, -130.313761, -130.313761, -130.3097, -130.305653, -130.30160899999998, -130.297553, -130.293512, -130.293512, -130.28947399999998, -130.285424, -130.281389, -130.277343, -130.273312, -130.273312, -130.26927, -130.265243, -130.261204, -130.257168,
            -130.363295, -130.359223, -130.355153, -130.355153, -130.35108599999998, -130.34702, -130.342956, -130.33889399999998, -130.334834, -130.334834, -130.330776, -130.326707, -130.322653, -130.318601, -130.314551, -130.314551, -130.31049, -130.306443, -130.302399, -130.298344, -130.29430299999999, -130.29430299999999, -130.290264, -130.286215, -130.28217999999998, -130.278134, -130.274103, -130.274103, -130.270062, -130.266035, -130.26199599999998, -130.25796,
            -130.364083, -130.360011, -130.355941, -130.355941, -130.35187399999998, -130.347808, -130.34374499999998, -130.339683, -130.335623, -130.335623, -130.33156499999998, -130.327496, -130.323442, -130.31939, -130.31534, -130.31534, -130.31127899999998, -130.307233, -130.303189, -130.29913399999998, -130.295094, -130.295094, -130.291055, -130.287006, -130.282971, -130.27892599999998, -130.274895, -130.274895, -130.270853, -130.26682599999998, -130.262788, -130.258752,
            -130.36487, -130.360799, -130.356729, -130.356729, -130.35266199999998, -130.348596, -130.34453299999998, -130.340471, -130.336412, -130.336412, -130.33235399999998, -130.328285, -130.324232, -130.32018, -130.31613, -130.31613, -130.312069, -130.308023, -130.303979, -130.299924, -130.295884, -130.295884, -130.291846, -130.287796, -130.283762, -130.279717, -130.275686, -130.275686, -130.271645, -130.267618, -130.26358, -130.259545,
            -130.365657, -130.361586, -130.357517, -130.357517, -130.35344999999998, -130.349384, -130.34532099999998, -130.34126, -130.3372, -130.3372, -130.333143, -130.329074, -130.325021, -130.320969, -130.31691999999998, -130.31691999999998, -130.312859, -130.308813, -130.304769, -130.300715, -130.296675, -130.296675, -130.29263699999998, -130.288587, -130.28455399999999, -130.280508, -130.276478, -130.276478, -130.272437, -130.26841, -130.26437199999998, -130.260337,
            -130.366445, -130.362373, -130.358304, -130.358304, -130.35423799999998, -130.35017299999998, -130.34610899999998, -130.342048, -130.337989, -130.337989, -130.333932, -130.329863, -130.32581, -130.321759, -130.317709, -130.317709, -130.313649, -130.30960299999998, -130.305559, -130.301505, -130.297465, -130.297465, -130.29342699999998, -130.289378, -130.285345, -130.281299, -130.277269, -130.277269, -130.273228, -130.269202, -130.265164, -130.26112899999998,
            -130.367232, -130.363161, -130.359092, -130.359092, -130.35502499999998, -130.35096, -130.34689699999998, -130.342836, -130.338777, -130.338777, -130.33472, -130.330652, -130.326599, -130.32254799999998, -130.318499, -130.318499, -130.314438, -130.310393, -130.30634999999998, -130.302295, -130.29825599999998, -130.29825599999998, -130.294218, -130.290169, -130.286136, -130.28208999999998, -130.27805999999998, -130.27805999999998, -130.27402, -130.269993, -130.265956, -130.261921,
            -130.368019, -130.363948, -130.35988, -130.35988, -130.35581299999998, -130.351748, -130.34768599999998, -130.343625, -130.339566, -130.339566, -130.335509, -130.33144099999998, -130.32738799999998, -130.32333699999998, -130.319288, -130.319288, -130.315228, -130.311183, -130.307139, -130.30308499999998, -130.299046, -130.299046, -130.295008, -130.29095999999998, -130.286926, -130.282882, -130.278852, -130.278852, -130.274811, -130.270785, -130.266748, -130.262713,
            -130.368806, -130.364736, -130.360667, -130.360667, -130.35660099999998, -130.352536, -130.34847399999998, -130.344413, -130.340354, -130.340354, -130.336298, -130.33222999999998, -130.32817699999998, -130.324126, -130.320077, -130.320077, -130.316017, -130.311972, -130.307929, -130.303875, -130.299836, -130.299836, -130.295799, -130.29175, -130.287717, -130.283673, -130.279643, -130.279643, -130.275603, -130.271577, -130.26754, -130.26350499999998,
            -130.36959299999998, -130.365523, -130.361455, -130.361455, -130.35738899999998, -130.353324, -130.34926199999998, -130.345201, -130.341143, -130.341143, -130.337086, -130.333019, -130.328966, -130.324915, -130.320867, -130.320867, -130.31680699999998, -130.312762, -130.308719, -130.304666, -130.300627, -130.300627, -130.29658899999998, -130.292541, -130.288508, -130.28446399999999, -130.28043399999999, -130.28043399999999, -130.27639399999998, -130.272368, -130.268332, -130.264297,
            -130.37037999999998, -130.36631, -130.36224199999998, -130.36224199999998, -130.358176, -130.354112, -130.35004999999998, -130.345989, -130.341931, -130.341931, -130.337875, -130.333807, -130.329755, -130.325705, -130.321656, -130.321656, -130.317597, -130.313552, -130.309509, -130.305456, -130.301417, -130.301417, -130.29738, -130.293332, -130.289299, -130.285255, -130.281225, -130.281225, -130.277185, -130.27316, -130.269123, -130.265089,
    };
}
