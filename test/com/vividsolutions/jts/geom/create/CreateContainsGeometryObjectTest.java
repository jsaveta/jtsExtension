/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vividsolutions.jts.geom.create;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.gml2.LineStringGenerator;
import java.util.Random;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author jsaveta
 */
public class CreateContainsGeometryObjectTest extends TestCase {

    public CreateContainsGeometryObjectTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of generateGeometry method, of class CreateContainsGeometryObject.
     */
    public void testGenerateGeometry() throws ParseException {
        
	GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        Geometry line = reader.read("LINESTRING (8.79232 48.92266, 8.79186 48.92246, 8.79135 48.92229, 8.79079 48.92219, 8.79012 48.92216, 8.78957 48.92207, 8.78903 48.92185, 8.78848 48.92162, 8.78807 48.92133, 8.78782 48.92097, 8.78771 48.92068, 8.78737 48.92031, 8.78695 48.92004, 8.78644 48.91974, 8.78604 48.91947, 8.78566 48.91923, 8.78531 48.91898, 8.78502 48.9188, 8.78459 48.91863, 8.78413 48.91849, 8.78357 48.91837, 8.78297 48.91841, 8.78252 48.91836, 8.78209 48.91825, 8.78168 48.91809, 8.7813 48.91795, 8.78084 48.9182, 8.78017 48.91855, 8.77919 48.91872, 8.77842 48.9189, 8.77763 48.91906, 8.77699 48.91933, 8.77736 48.91991, 8.77791 48.9206, 8.77865 48.9214, 8.77936 48.9222, 8.78014 48.92295, 8.78109 48.92359, 8.78226 48.92424, 8.78366 48.92485, 8.7851 48.9254, 8.78656 48.92593, 8.78798 48.92643, 8.78949 48.92698, 8.7908 48.92764, 8.79195 48.92844, 8.7929 48.92914, 8.79391 48.92981, 8.79523 48.93021, 8.7964 48.93056, 8.7975 48.93084, 8.79868 48.93097, 8.79987 48.93089, 8.80101 48.93075, 8.80194 48.93081, 8.80193 48.93081, 8.80193 48.93081, 8.80292 48.9311, 8.80376 48.9315, 8.80429 48.93211, 8.80467 48.93263, 8.80506 48.93317, 8.8055 48.93379, 8.80604 48.93441, 8.80657 48.93505, 8.80721 48.93577, 8.80795 48.93645, 8.80875 48.93713, 8.80942 48.93779, 8.81003 48.9385, 8.81074 48.93917, 8.81132 48.93985, 8.81154 48.94057, 8.81143 48.94131, 8.81168 48.94229, 8.81253 48.94314, 8.81357 48.94391, 8.81485 48.94456, 8.81608 48.94513, 8.81748 48.94574, 8.81892 48.94635, 8.82055 48.9469, 8.822 48.94735, 8.82335 48.94769, 8.82484 48.94789, 8.8264 48.94812, 8.82793 48.94827, 8.82949 48.94841, 8.83083 48.94855, 8.8318 48.9486, 8.83272 48.94857, 8.83364 48.94853, 8.83449 48.94843, 8.83537 48.94826, 8.83602 48.94817, 8.83689 48.94793, 8.83769 48.94763, 8.83836 48.94725, 8.83897 48.94681, 8.83936 48.94633, 8.83981 48.94581, 8.84058 48.94549, 8.84153 48.9455, 8.84205 48.94571, 8.84249 48.94602, 8.84292 48.94631, 8.84387 48.94657, 8.84489 48.9466, 8.84591 48.94652, 8.84683 48.94655, 8.84776 48.94666, 8.84872 48.94681, 8.84967 48.94696, 8.85078 48.94712, 8.8521 48.94733, 8.85353 48.94762, 8.85498 48.94786, 8.85656 48.9481, 8.85813 48.9484, 8.85946 48.94864, 8.861 48.9489, 8.86253 48.94917, 8.86407 48.94945, 8.8656 48.94973, 8.86714 48.95, 8.86863 48.95027, 8.86989 48.9505, 8.87113 48.95074, 8.87232 48.95097, 8.87377 48.9512, 8.8751 48.95133, 8.87641 48.95132, 8.87766 48.95121, 8.87891 48.95099, 8.88056 48.95086, 8.88209 48.95079, 8.8836 48.95071, 8.88508 48.95065, 8.88643 48.95064, 8.88776 48.95071, 8.889 48.95086, 8.89007 48.95093, 8.89111 48.95099, 8.89248 48.9511, 8.89379 48.95132, 8.89508 48.95159, 8.89662 48.95178, 8.89817 48.95194, 8.89968 48.95211, 8.90118 48.95235, 8.90269 48.95256, 8.90422 48.95278, 8.90578 48.95292, 8.90733 48.95314, 8.90861 48.95328, 8.9098 48.95317, 8.91083 48.95298, 8.91182 48.95291, 8.91233 48.95305, 8.91268 48.95321, 8.91297 48.95316, 8.91292 48.95314, 8.91348 48.953, 8.91431 48.95278, 8.91514 48.95255, 8.91607 48.9523, 8.91711 48.95205, 8.91821 48.95176, 8.91941 48.95143, 8.92065 48.95112, 8.92195 48.9508, 8.92321 48.95047, 8.92443 48.95016, 8.92567 48.94984, 8.92693 48.94953, 8.92816 48.9492, 8.92941 48.94889, 8.93061 48.94854, 8.93182 48.94821, 8.93284 48.94768, 8.9336 48.94702, 8.9342 48.94625, 8.93479 48.94544, 8.93536 48.94468, 8.93617 48.94399, 8.93718 48.94349, 8.93828 48.94313, 8.93948 48.94293, 8.94069 48.94274, 8.94193 48.94254, 8.94302 48.94242, 8.94403 48.94225, 8.94477 48.94208, 8.94525 48.94186, 8.94561 48.94172, 8.94536 48.94181, 8.94531 48.94185, 8.94548 48.94195, 8.94567 48.94179, 8.94534 48.94172, 8.94555 48.94185, 8.94596 48.94215, 8.94641 48.94249, 8.94695 48.94288, 8.94756 48.94333, 8.94829 48.9438, 8.94913 48.94417, 8.95002 48.9445, 8.95096 48.94471, 8.95195 48.94492, 8.95283 48.94525, 8.9536 48.94562, 8.95438 48.946, 8.955 48.94634, 8.95575 48.94658, 8.9566 48.94688, 8.95735 48.94727, 8.95803 48.9478, 8.95871 48.94831, 8.95954 48.94881, 8.96033 48.94933, 8.96112 48.94991, 8.96191 48.95049, 8.96281 48.95095, 8.96383 48.95136, 8.96437 48.95202, 8.9644 48.95251, 8.96442 48.95297, 8.96454 48.95328, 8.96519 48.95321, 8.96578 48.95315, 8.96634 48.95297, 8.96661 48.9526, 8.96671 48.95216, 8.96684 48.95175, 8.96709 48.95136, 8.96786 48.95142, 8.96866 48.95145, 8.96947 48.95142, 8.9697 48.95142, 8.96984 48.95142, 8.96984 48.95143, 8.96983 48.95142, 8.96984 48.95141, 8.96975 48.95136, 8.96973 48.95137, 8.97023 48.95157, 8.97094 48.95184, 8.97164 48.95206, 8.97249 48.95222, 8.97326 48.95249, 8.974 48.95282, 8.97477 48.95315, 8.97567 48.95334, 8.97657 48.95356, 8.97747 48.95376, 8.97829 48.95397, 8.97909 48.95417, 8.97989 48.95433, 8.98069 48.95441, 8.98152 48.95449, 8.98239 48.95453, 8.98327 48.95458, 8.98413 48.95458, 8.9848 48.9548, 8.98545 48.95473, 8.98533 48.95453, 8.98484 48.95429, 8.9847 48.95455, 8.98498 48.95512, 8.98534 48.95569, 8.98592 48.95621, 8.98669 48.95664, 8.98761 48.95685, 8.98868 48.95684, 8.98988 48.95664, 8.9911 48.9565, 8.99201 48.95661, 8.99292 48.95667, 8.99384 48.95683, 8.99438 48.95669, 8.99493 48.95657, 8.99555 48.9562, 8.99603 48.95576, 8.99627 48.95522, 8.99631 48.95451, 8.99653 48.95376, 8.99701 48.95303, 8.99763 48.9523, 8.99859 48.95149, 8.99977 48.95093, 9.00121 48.95057, 9.00276 48.95036, 9.00433 48.95033, 9.00583 48.95033, 9.00748 48.95034, 9.00913 48.95035, 9.01052 48.95035, 9.01173 48.95038, 9.01302 48.95034, 9.01433 48.95031, 9.01562 48.95028, 9.01701 48.95022, 9.01782 48.95005, 9.01852 48.95021, 9.02001 48.9503, 9.02153 48.95035, 9.02294 48.95028, 9.02445 48.95013, 9.02594 48.95012, 9.02743 48.95018, 9.02894 48.95021, 9.03042 48.95038, 9.03188 48.95054, 9.0334 48.95053, 9.03491 48.95049, 9.03643 48.95043, 9.03794 48.95025, 9.03947 48.95013, 9.04107 48.94999, 9.04264 48.95003, 9.04423 48.95007, 9.04575 48.95019, 9.04712 48.95029, 9.0485 48.95046, 9.04971 48.95063, 9.05091 48.95077, 9.0521 48.95089, 9.05329 48.95096, 9.05448 48.95097, 9.05568 48.95115, 9.05686 48.95122, 9.0573 48.95172, 9.05799 48.9522, 9.05861 48.95262, 9.05873 48.9527, 9.05873 48.95272, 9.05874 48.95272, 9.05898 48.95295, 9.05832 48.95332, 9.05781 48.95386, 9.05747 48.95457, 9.05749 48.9553, 9.05747 48.95603, 9.0573 48.95676, 9.05722 48.95749, 9.05715 48.95824, 9.05663 48.95886, 9.05671 48.95872, 9.05653 48.95837, 9.05574 48.95819, 9.05488 48.95824, 9.05409 48.95807, 9.05332 48.95778, 9.05252 48.95754, 9.05169 48.95741, 9.05076 48.9573, 9.04984 48.95721, 9.04893 48.95717, 9.04811 48.95739, 9.0472 48.95738, 9.04626 48.95736, 9.04531 48.95735, 9.04438 48.95732, 9.04331 48.9573, 9.04163 48.9572, 9.04121 48.95718, 9.04193 48.95718, 9.04261 48.95721, 9.04329 48.95724, 9.04401 48.95726, 9.04483 48.95728, 9.04565 48.95731, 9.04635 48.9573, 9.04718 48.9573, 9.04802 48.9573, 9.04887 48.9572, 9.04966 48.95717, 9.05047 48.95727, 9.05117 48.95735, 9.05198 48.95746, 9.0529 48.95767, 9.05356 48.95793, 9.05422 48.95819, 9.05483 48.95831, 9.05556 48.95819, 9.05628 48.95824, 9.05691 48.95845, 9.05675 48.95857, 9.05652 48.95871, 9.05637 48.95892, 9.05567 48.95906, 9.05485 48.95922, 9.05415 48.95951, 9.05373 48.95996, 9.05366 48.9605, 9.05383 48.96077, 9.05424 48.96107, 9.05472 48.96133, 9.05523 48.9616, 9.05571 48.9619, 9.05612 48.96222, 9.05645 48.9625, 9.05669 48.96275, 9.05691 48.96298, 9.05758 48.96276, 9.05845 48.96244, 9.05933 48.96209, 9.06011 48.96169, 9.06079 48.96125, 9.06118 48.96168, 9.06134 48.96224, 9.06164 48.96285, 9.06264 48.96304, 9.06381 48.96295, 9.06493 48.96302, 9.06563 48.96365, 9.06629 48.96431, 9.0666 48.96503, 9.06706 48.9657, 9.06765 48.96626, 9.06799 48.96688, 9.06824 48.96745, 9.0685 48.96806, 9.06902 48.96835, 9.06973 48.96835, 9.07035 48.96839, 9.0708 48.9686, 9.07123 48.96877, 9.07172 48.96886, 9.0718 48.96886, 9.07181 48.96885, 9.07186 48.96886, 9.072 48.9691, 9.07194 48.96944, 9.07177 48.96979, 9.07153 48.97013, 9.07128 48.97051, 9.07108 48.97101, 9.07105 48.97157, 9.07109 48.97216, 9.07135 48.97274, 9.07186 48.97352, 9.0724 48.97438, 9.07302 48.97535, 9.07368 48.97636, 9.07449 48.97735, 9.07565 48.9783, 9.07685 48.97927, 9.0779 48.98025, 9.07901 48.9812, 9.08022 48.98217, 9.08164 48.98291, 9.08333 48.98343, 9.08481 48.98418, 9.08601 48.985, 9.08693 48.98594, 9.08765 48.98695, 9.08857 48.98789, 9.0896 48.98878, 9.09074 48.98961, 9.09169 48.99044, 9.09277 48.99051, 9.09358 48.99078, 9.09425 48.99145, 9.09509 48.99211, 9.09607 48.99267, 9.09706 48.99321, 9.09826 48.99386, 9.09934 48.99456, 9.1005 48.9953, 9.10193 48.99569, 9.10319 48.99571, 9.10417 48.99574, 9.10467 48.99573, 9.1057 48.99573, 9.1072 48.99565, 9.10872 48.99554, 9.11021 48.99541, 9.11174 48.99528, 9.11336 48.99514, 9.11501 48.99508, 9.11643 48.99542, 9.11719 48.99614, 9.11766 48.99703, 9.11816 48.99762, 9.11905 48.99801, 9.12008 48.99816, 9.12117 48.99789, 9.12268 48.99754, 9.12415 48.99714, 9.12557 48.99671, 9.12688 48.9962, 9.12831 48.9958, 9.12976 48.99571, 9.13102 48.99568, 9.1322 48.99569, 9.13334 48.9956, 9.13445 48.99553, 9.13559 48.99558, 9.13674 48.9957, 9.13779 48.99578, 9.13837 48.99607, 9.13832 48.99656, 9.1386 48.99693, 9.13916 48.99709, 9.13988 48.9971, 9.14035 48.99694, 9.1408 48.99672, 9.14112 48.99643, 9.14145 48.99609, 9.14185 48.99575, 9.1424 48.99563, 9.1428 48.99582, 9.14308 48.99628, 9.14365 48.99672, 9.14427 48.99713, 9.14493 48.99757, 9.1456 48.998, 9.14591 48.99857, 9.14579 48.99917, 9.14514 48.99955, 9.1446 48.99989, 9.14407 49.00032, 9.14363 49.00076, 9.14321 49.0012, 9.14282 49.00166, 9.14264 49.00181, 9.14263 49.00181, 9.1424 49.00214, 9.14273 49.00254, 9.14342 49.00277, 9.1441 49.003, 9.14484 49.00314, 9.14567 49.00302, 9.14658 49.00307, 9.1475 49.00316, 9.14844 49.00325, 9.14934 49.00333, 9.15024 49.00342, 9.15115 49.00352, 9.15205 49.00362, 9.15288 49.00384, 9.15343 49.00394, 9.15342 49.00393, 9.15342 49.00393, 9.15337 49.00394, 9.15337 49.00394, 9.15342 49.0039, 9.15373 49.00391, 9.15452 49.00396, 9.1554 49.00414, 9.15624 49.00456, 9.1571 49.00507, 9.15799 49.00557, 9.15889 49.00607, 9.15991 49.00654, 9.1609 49.0069, 9.16202 49.00712, 9.16319 49.00717, 9.16438 49.00717, 9.16556 49.00711, 9.1667 49.00701, 9.16784 49.0068, 9.16884 49.00635, 9.16982 49.00576, 9.1707 49.00522, 9.17155 49.0047, 9.17237 49.00415, 9.17317 49.0036, 9.17395 49.00305, 9.17464 49.00245, 9.17519 49.00179, 9.17569 49.0011, 9.17615 49.00038, 9.17656 48.99969, 9.17685 48.99897, 9.17702 48.9982, 9.17717 48.99745, 9.17745 48.9967, 9.17795 48.99602, 9.17879 48.99551, 9.17976 48.99509, 9.18067 48.99468, 9.1812 48.99432, 9.18185 48.994, 9.18246 48.99378, 9.18306 48.99357, 9.18358 48.9934, 9.18414 48.99319, 9.18476 48.9929, 9.18523 48.99266, 9.18568 48.99244, 9.18617 48.99237, 9.1865 48.99244, 9.18708 48.99252, 9.18763 48.99242, 9.18764 48.99219, 9.18737 48.99168, 9.18727 48.99144, 9.18747 48.99177, 9.18759 48.99202, 9.18769 48.99225, 9.18793 48.99225, 9.18839 48.99202, 9.1889 48.99187, 9.18932 48.99182, 9.1898 48.99172, 9.19001 48.99181, 9.19021 48.99225, 9.19027 48.99262, 9.1904 48.99303, 9.19041 48.99348, 9.19046 48.994, 9.19051 48.99451, 9.19056 48.99504, 9.19061 48.99556, 9.19057 48.99612, 9.19055 48.99681, 9.19063 48.99773, 9.19101 48.99872, 9.19149 48.99972, 9.19212 49.00067, 9.19304 49.00151, 9.19407 49.00231, 9.19526 49.00302, 9.1966 49.00362, 9.19808 49.00406, 9.19962 49.00415, 9.20091 49.00424, 9.20213 49.00436, 9.20326 49.00422, 9.204 49.00374, 9.20432 49.0031, 9.20445 49.00244, 9.20471 49.00185, 9.20503 49.00143, 9.2057 49.00128, 9.20626 49.00109, 9.20666 49.00084, 9.20729 49.00077, 9.20785 49.00071, 9.20819 49.00069, 9.20821 49.00076, 9.20815 49.00073, 9.20865 49.00081, 9.20924 49.00084, 9.20985 49.00085, 9.2104 49.00075, 9.21094 49.00054, 9.21153 49.00031, 9.21221 49.0002, 9.2129 49.00019, 9.21357 49.00029, 9.21407 49.0006, 9.21456 49.00092, 9.21503 49.0015, 9.21545 49.00187, 9.21625 49.002, 9.21708 49.00185, 9.21827 49.00192, 9.21941 49.00212, 9.22057 49.00239, 9.22172 49.00278, 9.22307 49.00302, 9.22436 49.00329, 9.22517 49.00362, 9.22596 49.00385, 9.22727 49.00394, 9.22875 49.00403, 9.23023 49.0042, 9.23169 49.00439, 9.23305 49.00459, 9.23413 49.00455, 9.23498 49.00464, 9.23582 49.00475, 9.23674 49.00502, 9.23782 49.00523, 9.23876 49.00545, 9.2397 49.00568, 9.24027 49.00536, 9.23989 49.00486, 9.23915 49.00444, 9.23832 49.00442, 9.23808 49.00515, 9.23875 49.0061, 9.24011 49.00729, 9.24157 49.00863, 9.24304 49.00992, 9.24447 49.01124, 9.24575 49.0127, 9.24673 49.01427, 9.24743 49.0159, 9.24788 49.01757, 9.24812 49.01923, 9.2486 49.02086, 9.24942 49.02245, 9.25042 49.024, 9.25133 49.02514, 9.25212 49.02628, 9.25275 49.02744, 9.25321 49.02865, 9.25346 49.02983, 9.25366 49.03108, 9.25381 49.03231, 9.25417 49.03356, 9.25465 49.03485, 9.25529 49.03598, 9.25609 49.03711, 9.25714 49.03817, 9.25867 49.03959, 9.26019 49.04095, 9.26169 49.04232, 9.26311 49.04361, 9.26448 49.04488, 9.26568 49.04614, 9.26671 49.04743, 9.26755 49.04885, 9.2677 49.05034, 9.26793 49.05188, 9.2682 49.0535, 9.26847 49.05503, 9.26877 49.05659, 9.2691 49.05807, 9.26985 49.05952, 9.27091 49.06089, 9.27238 49.06221, 9.27394 49.06351, 9.27549 49.0648, 9.27698 49.06608, 9.27833 49.06746, 9.27953 49.06892, 9.28071 49.07036, 9.28189 49.07181, 9.28303 49.07328, 9.28414 49.07472, 9.28522 49.07619, 9.28651 49.07786, 9.2877 49.07939, 9.28884 49.08085, 9.2897 49.08236, 9.29023 49.08396, 9.29061 49.08539, 9.2909 49.08663, 9.29121 49.08791, 9.2916 49.08929, 9.292 49.09077, 9.29237 49.09223, 9.2928 49.09373, 9.29315 49.09511, 9.29368 49.09657, 9.29456 49.09785, 9.29579 49.09909, 9.29709 49.1003, 9.29825 49.10152, 9.29943 49.10275, 9.30058 49.10397, 9.30168 49.10524, 9.3024 49.10662, 9.30283 49.10806, 9.303 49.10954, 9.30317 49.11102, 9.30324 49.11249, 9.30313 49.11396, 9.30278 49.11542, 9.30213 49.11693, 9.3014 49.11839, 9.30078 49.11988, 9.30042 49.12137, 9.30029 49.12289, 9.30031 49.1244, 9.30046 49.12591, 9.30068 49.12741, 9.30101 49.12891, 9.30146 49.13037, 9.30213 49.13178, 9.30282 49.13308, 9.30369 49.13435, 9.3046 49.13564, 9.30537 49.13692, 9.30595 49.13821, 9.3063 49.13941, 9.30635 49.14068, 9.30617 49.14193, 9.30578 49.14317, 9.30532 49.14439, 9.30481 49.1456, 9.3043 49.14677, 9.3038 49.14776, 9.30363 49.14819, 9.30361 49.14818, 9.30311 49.14887, 9.30241 49.14977, 9.30175 49.15068, 9.30123 49.15156, 9.30067 49.15248, 9.30018 49.15339, 9.29997 49.15419, 9.30015 49.15498, 9.30082 49.15562, 9.30177 49.15614, 9.30298 49.1564, 9.30438 49.15648, 9.3058 49.15647, 9.30742 49.15642, 9.30907 49.15637, 9.31072 49.15637, 9.3124 49.15649, 9.31407 49.15671, 9.31569 49.15705, 9.31723 49.15751, 9.31886 49.15815, 9.32027 49.15888, 9.32165 49.15967, 9.32301 49.16045, 9.32448 49.16114, 9.32608 49.16171, 9.32777 49.16212, 9.32953 49.16238, 9.33134 49.16248, 9.33314 49.16243, 9.33496 49.16224, 9.33672 49.16195, 9.33878 49.16154, 9.34049 49.16123, 9.34219 49.16094, 9.34384 49.16065, 9.34549 49.16041, 9.34721 49.16015, 9.34889 49.16, 9.3506 49.15997, 9.35231 49.15999, 9.35406 49.16012, 9.35579 49.16027, 9.35752 49.16044, 9.35924 49.1606, 9.36098 49.16076, 9.36272 49.16092, 9.36446 49.1611, 9.36538 49.16125, 9.36538 49.16126, 9.36537 49.16125, 9.36538 49.16124, 9.36529 49.16119, 9.36527 49.1612, 9.36605 49.16137, 9.36765 49.16182, 9.36914 49.16234, 9.37054 49.16299, 9.37191 49.16376, 9.37316 49.16454, 9.37417 49.16544, 9.37514 49.16641, 9.37597 49.16746, 9.37676 49.16853, 9.37764 49.16955, 9.37856 49.17058, 9.37977 49.17145, 9.38113 49.17218, 9.38262 49.17278, 9.38422 49.17325, 9.38591 49.17356, 9.38772 49.17369, 9.38955 49.17384, 9.39135 49.17398, 9.39311 49.17418, 9.3948 49.1744, 9.39658 49.17484, 9.39823 49.17526, 9.39987 49.1757, 9.40154 49.17617, 9.40318 49.17662, 9.40483 49.17696, 9.40663 49.17737, 9.40827 49.1778, 9.40979 49.17839, 9.41124 49.17922, 9.41259 49.17992, 9.41339 49.1803, 9.41406 49.1804, 9.41439 49.18009, 9.41403 49.17975, 9.41357 49.17944, 9.41312 49.17933, 9.41232 49.17955, 9.4116 49.17991, 9.41098 49.18026, 9.41024 49.18052, 9.40953 49.18077, 9.40885 49.18105, 9.40812 49.18122, 9.40739 49.18135, 9.40668 49.18153, 9.40584 49.18167, 9.40513 49.18184, 9.40477 49.18194, 9.40438 49.18201, 9.40391 49.18204, 9.40351 49.18205, 9.40311 49.18207, 9.40272 49.18211, 9.40237 49.18214, 9.40191 49.18183, 9.4013 49.18157, 9.40053 49.18133, 9.39974 49.1811, 9.39877 49.1808, 9.39785 49.18054, 9.39693 49.18029, 9.3959 49.17999, 9.3947 49.17976, 9.39332 49.1797, 9.39267 49.1797, 9.3926 49.17966, 9.39261 49.17967, 9.39272 49.17965, 9.39267 49.17959, 9.39269 49.17969, 9.39268 49.17971, 9.39153 49.17969, 9.39001 49.17972, 9.38846 49.17976, 9.3869 49.17976, 9.38538 49.17977, 9.38373 49.17971, 9.38221 49.17968, 9.38066 49.17962, 9.37918 49.17955, 9.37783 49.17945, 9.37659 49.17922, 9.37539 49.17892, 9.37415 49.17859, 9.37321 49.17851, 9.37255 49.17867, 9.37191 49.17883, 9.37126 49.17899, 9.37059 49.17904, 9.36991 49.17909, 9.36976 49.17946, 9.37014 49.17983, 9.37001 49.18027, 9.36975 49.18066, 9.36932 49.18101, 9.3689 49.18134, 9.36843 49.18159, 9.36785 49.18177, 9.36725 49.18192, 9.36668 49.18217, 9.36621 49.18239, 9.36574 49.18263, 9.36521 49.18285, 9.36496 49.18319, 9.36497 49.18357, 9.36439 49.18366, 9.36387 49.18374, 9.3633 49.18385, 9.36268 49.18386, 9.36206 49.18388, 9.3614 49.1839, 9.36079 49.18389, 9.36015 49.18386, 9.35953 49.18385, 9.3589 49.18383, 9.35835 49.184, 9.35794 49.18429, 9.35752 49.18463, 9.35706 49.18486, 9.35648 49.18499, 9.35594 49.18518, 9.35596 49.18556, 9.35626 49.18592, 9.35667 49.18623, 9.35764 49.18676)");

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            LineStringGenerator pg = new LineStringGenerator();
            pg.setGeometryFactory(geometryFactory);
            pg.setBoundingBox(new Envelope(-180, 180, -90, 90));

            //10 to 350 points (check generation of larger linestrings)
            int numPoints = rand.nextInt(350) + 10;
            pg.setNumberPoints(numPoints);

//            LineString line = (LineString) pg.create();
            CreateContainsGeometryObject instanceL1 = new CreateContainsGeometryObject(line, GeometryType.GeometryTypes.LineString);

            Geometry resultL1 = instanceL1.generateGeometry();
            System.out.println("line: " + line);
            System.out.println("result: " + resultL1);
            assertTrue(resultL1.isValid());
            assertTrue(line.contains(resultL1));
        }
    }

}
