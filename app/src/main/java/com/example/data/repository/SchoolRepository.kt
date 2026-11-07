package com.example.data.repository

import com.example.data.model.AdmissionDocument
import com.example.data.model.FaqItem
import com.example.data.model.FormalAdmissionApplication
import com.example.data.model.GradeInfo
import com.example.data.model.MdmMenuItem
import com.example.data.model.NoticeCategory
import com.example.data.model.SchoolHoliday
import com.example.data.model.SchoolNotice
import com.example.data.model.SchoolValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class SchoolRepository {

    val schoolPhone = "9471471275"
    val vicePrincipalName = "Mr. Janardan Ojha"
    val vicePrincipalNameHi = "श्री जनार्दन ओझा"
    val schoolAddress = "Nehrua Kala, Panchdeori, Gopalganj, Bihar"
    val schoolAddressHi = "नेहरुवा कला, पंचदेवरी, गोपालगंज, बिहार"
    val schoolGeoUri = "geo:26.4716,84.4419?q=Nehrua+Kala,+Panchdeori,+Gopalganj"

    val values = listOf(
        SchoolValue(
            title = "Quality Education",
            titleHi = "गुणवत्तापूर्ण शिक्षा",
            description = "Building strong foundations for the future with SCERT & NCERT curriculum.",
            descriptionHi = "मजबूत नींव और उत्तम पठन-पाठन से बच्चों के सुनहरे भविष्य का निर्माण।",
            iconEmoji = "📖"
        ),
        SchoolValue(
            title = "Holistic Development",
            titleHi = "सर्वांगीण विकास",
            description = "Supporting every student's intellectual, emotional and physical growth.",
            descriptionHi = "बौद्धिक, मानसिक एवं शारीरिक क्षमताओं का संतुलित विकास।",
            iconEmoji = "🌱"
        ),
        SchoolValue(
            title = "Discipline & Values",
            titleHi = "अनुशासन एवं संस्कार",
            description = "Encouraging respect, integrity, moral grounding and civic responsibility.",
            descriptionHi = "सदाचार, परस्पर सम्मान और नैतिक संस्कारों का निरंतर समावेश।",
            iconEmoji = "🏆"
        ),
        SchoolValue(
            title = "Supportive Environment",
            titleHi = "सहयोगात्मक वातावरण",
            description = "Learning and growing together with caring teachers and a safe campus.",
            descriptionHi = "स्नेहशील शिक्षकों और सुरक्षित परिसर में सहज शिक्षा।",
            iconEmoji = "🤝"
        )
    )

    val admissionDocuments = listOf(
        AdmissionDocument(
            id = "aadhaar_child",
            title = "Child's Aadhaar Card",
            titleHi = "विद्यार्थी का आधार कार्ड",
            requirement = "Original and 2 self-attested photocopies of the child's Aadhaar card.",
            requirementHi = "बच्चे के आधार कार्ड की मूल प्रति और दो छायाप्रतियां।",
            tip = "Date of birth must match birth registration certificate.",
            tipHi = "आधार में जन्मतिथि जन्म प्रमाण पत्र के अनुसार होनी चाहिए।",
            isMandatory = true
        ),
        AdmissionDocument(
            id = "aadhaar_parents",
            title = "Parents' Aadhaar Cards",
            titleHi = "माता-पिता का आधार कार्ड",
            requirement = "Aadhaar cards of both Father and Mother (or legal guardian).",
            requirementHi = "माता एवं पिता दोनों के आधार कार्ड की छायाप्रति।",
            tip = "Required for official enrollment register and Medhasoft scholarship verification.",
            tipHi = "मेधासॉफ्ट पोर्टल और विद्यालय नामांकन पंजिका हेतु अनिवार्य।",
            isMandatory = true
        ),
        AdmissionDocument(
            id = "bank_account",
            title = "Bank Account Passbook / Details",
            titleHi = "बैंक खाता पासबुक विवरण",
            requirement = "Child < 10 years: Parent's bank account. Child 10+ years: Child's own active bank account with IFSC.",
            requirementHi = "10 वर्ष से कम: माता/पिता का बैंक खाता। 10 वर्ष या अधिक: बच्चे का स्वयं का बचत खाता।",
            tip = "Critical for Government DBT benefits (Direct Benefit Transfer) for uniforms and scholarships.",
            tipHi = "पोशाक राशि और छात्रवृत्ति के प्रत्यक्ष लाभ अंतरण (DBT) के लिए आवश्यक।",
            isMandatory = true
        ),
        AdmissionDocument(
            id = "photograph",
            title = "Passport-size Photographs",
            titleHi = "पासपोर्ट साइज रंगीन फोटो",
            requirement = "Recent passport-size color photographs of the child (4 copies).",
            requirementHi = "विद्यार्थी की हाल ही में खींची गई 4 पासपोर्ट साइज फोटो।",
            tip = "Light background with clear facial view.",
            tipHi = "साफ पृष्ठभूमि और स्पष्ट चेहरे वाली फोटो लाएं।",
            isMandatory = true
        ),
        AdmissionDocument(
            id = "transfer_cert",
            title = "Transfer Certificate (TC)",
            titleHi = "स्थानांतरण प्रमाण पत्र (TC)",
            requirement = "Original TC from the previous school if migrating from another institution.",
            requirementHi = "दूसरे विद्यालय से आने पर पूर्व विद्यालय का मूल स्थानांतरण प्रमाण पत्र।",
            tip = "Required for Class 2 onwards; not needed for fresh enrollment into Class 1.",
            tipHi = "वर्ग 1 में सीधे नामांकन के लिए अनिवार्य नहीं है।",
            isMandatory = false
        ),
        AdmissionDocument(
            id = "birth_cert",
            title = "Birth Certificate (Janam Praman Patra)",
            titleHi = "जन्म प्रमाण पत्र",
            requirement = "Issued by Gram Panchayat, Hospital, or Nagar Palika.",
            requirementHi = "ग्राम पंचायत, अस्पताल या नगर निकाय द्वारा निर्गत प्रमाण पत्र।",
            tip = "Used for age verification under Right to Education (RTE).",
            tipHi = "उम्र सत्यापन के लिए मुख्य दस्तावेज।",
            isMandatory = true
        ),
        AdmissionDocument(
            id = "caste_income",
            title = "Category / Caste Certificate (If applicable)",
            titleHi = "जाति / आय प्रमाण पत्र (यदि लागू हो)",
            requirement = "SC/ST/EBC/OBC/BPL documentation for state welfare scholarship quotas.",
            requirementHi = "आरक्षित वर्ग छात्रवृत्ति हेतु सक्षम पदाधिकारी द्वारा निर्गत प्रमाण पत्र।",
            tip = "Ensures eligible student benefits are disbursed without delay.",
            tipHi = "समय पर छात्रवृत्ति अनुदान प्राप्त करने में सहायक।",
            isMandatory = false
        )
    )

    val grades = listOf(
        GradeInfo(
            standard = "Primary Wing (Class 1 - 5)",
            standardHi = "प्राथमिक प्रभाग (वर्ग 1 से 5)",
            ageGroup = "Ages 6 to 10 years",
            subjects = listOf("Hindi", "English", "Ganit (Maths)", "Paryavaran (EVS)", "Drawing & Art", "Physical Exercises"),
            description = "Fostering foundational literacy, numeracy, creative expression, and curiosity through activity-based learning.",
            descriptionHi = "खेल-खेल में शिक्षा, बुनियादी भाषा ज्ञान, गणितीय कौशल एवं रचनात्मक गतिविधियों का विकास।"
        ),
        GradeInfo(
            standard = "Upper Primary / Middle Wing (Class 6 - 8)",
            standardHi = "उच्च प्राथमिक / मध्य प्रभाग (वर्ग 6 से 8)",
            ageGroup = "Ages 11 to 14 years",
            subjects = listOf("Hindi", "English", "Ganit (Mathematics)", "Vigyan (Science)", "Samajik Vigyan (Social Science)", "Sanskrit / Urdu", "Moral Science & Sports"),
            description = "Empowering middle school scholars with critical thinking, scientific inquiry, language proficiency, and civic understanding.",
            descriptionHi = "वैज्ञानिक दृष्टिकोण, तार्किक चिंतन, सामाजिक समझ और माध्यमिक शिक्षा की मजबूत तैयारी।"
        )
    )

    val mdmMenu = listOf(
        MdmMenuItem(
            day = "Monday",
            dayHi = "सोमवार",
            mealName = "Chawal + Dal + Seasonal Sabzi",
            mealNameHi = "चावल + दाल + हरी मौसमी सब्जी",
            details = "Freshly steamed rice, protein-rich Arhar/Moong dal, and local fresh vegetable curry.",
            detailsHi = "गरमा-गरम उबले चावल, अरहर/मूंग की दाल और ताजी पौष्टिक सब्जी।",
            nutritionNotes = "High protein and essential vitamins"
        ),
        MdmMenuItem(
            day = "Tuesday",
            dayHi = "मंगलवार",
            mealName = "Khichdi + Chokha / Achar",
            mealNameHi = "खिचड़ी + चोखा / अचार",
            details = "Wholesome dal-rice khichdi cooked with turmeric, green peas/spinach, served with spiced potato chokha.",
            detailsHi = "दाल और चावल से बनी स्वादिष्ट खिचड़ी एवं आलू का चोखा।",
            nutritionNotes = "Easy digestion and wholesome carbs"
        ),
        MdmMenuItem(
            day = "Wednesday",
            dayHi = "बुधवार",
            mealName = "Chawal + Soya Sabzi / Dal",
            mealNameHi = "चावल + सोयाबीन बड़ी की सब्जी + दाल",
            details = "Steamed rice served with soybean chunk curry and seasoned yellow dal.",
            detailsHi = "चावल के साथ पौष्टिक सोयाबीन बड़ी की मसालेदार सब्जी और दाल।",
            nutritionNotes = "Rich in plant protein & iron"
        ),
        MdmMenuItem(
            day = "Thursday",
            dayHi = "गुरुवार",
            mealName = "Chawal + Mix Veg Dal + Salad",
            mealNameHi = "चावल + मिश्रित सब्जी दाल + सलाद",
            details = "Nutrient-packed mixed vegetable dal, rice, cucumber/onion salad.",
            detailsHi = "विभिन्न सब्जियों से युक्त दाल, चावल और खीरा/टमाटर सलाद।",
            nutritionNotes = "Dietary fiber and micronutrients"
        ),
        MdmMenuItem(
            day = "Friday",
            dayHi = "शुक्रवार",
            mealName = "Special Khichdi / Boiled Egg or Seasonal Fruit",
            mealNameHi = "विशेष खिचड़ी + उबला अंडा (शाकाहारी को मौसमी फल)",
            details = "Nutritious khichdi served with boiled egg or banana/seasonal fruit for vegetarian students.",
            detailsHi = "पौष्टिक खिचड़ी के साथ उबला अंडा या शाकाहारी छात्रों के लिए केला/फल।",
            nutritionNotes = "Protein boost & essential minerals"
        ),
        MdmMenuItem(
            day = "Saturday",
            dayHi = "शनिवार",
            mealName = "Chawal + Chana Dal Kaddu Sabzi",
            mealNameHi = "चावल + चना दाल एवं कद्दू सब्जी",
            details = "Nutritious chana dal cooked with pumpkin/gourd, accompanied by steamed rice.",
            detailsHi = "चना दाल और कद्दू (लौकी) की स्वादिष्ट सब्जी एवं चावल।",
            nutritionNotes = "Energy sustenance and iron"
        )
    )

    val notices = listOf(
        SchoolNotice(
            id = "not-01",
            title = "New Academic Session Admissions Open (2025–26)",
            titleHi = "नवीन शैक्षणिक सत्र (2025-26) में नामांकन प्रारंभ",
            date = "Active Now",
            category = NoticeCategory.ADMISSIONS,
            summary = "Admissions are currently underway for Class 1 to 8 at UMS Nehrua Kala.",
            summaryHi = "वर्ग 1 से 8 तक के सभी इच्छुक छात्र-छात्राओं का नामांकन जारी है।",
            details = "Parents and guardians are cordially invited to visit the school campus between 9:30 AM and 1:30 PM on working days. Please carry child and parent Aadhaar cards, bank details, and 4 passport photographs.",
            detailsHi = "अभिभावक कार्यदिवस में सुबह 9:30 से दोपहर 1:30 बजे तक विद्यालय आकर नामांकन कराएं। आधार कार्ड, बैंक खाता व फोटो अवश्य साथ लाएं।",
            isUrgent = true
        ),
        SchoolNotice(
            id = "not-02",
            title = "Medhasoft Scholarship & DBT Account Verification",
            titleHi = "मेधासॉफ्ट छात्रवृत्ति एवं बैंक खाता सत्यापन सूचना",
            date = "Notice #24",
            category = NoticeCategory.ADMISSIONS,
            summary = "Mandatory bank account and Aadhaar seeding for uniform and scholarship grants.",
            summaryHi = "पोशाक राशि और छात्रवृत्ति हेतु बैंक खाता आधार से लिंक होना अनिवार्य है।",
            details = "All parents must verify that the child's bank account (or parent's account for children below 10 years) is active and linked with NPCI/Aadhaar for smooth disbursement of the annual uniform grant and Bihar State scholarship funds.",
            detailsHi = "सभी अभिभावक यह सुनिश्चित करें कि खाता सक्रिय है ताकि सरकारी अनुदान सीधे खाते में प्राप्त हो सके।",
            isUrgent = false
        ),
        SchoolNotice(
            id = "not-03",
            title = "Free Textbooks & Notebook Distribution",
            titleHi = "निःशुल्क पाठ्यपुस्तक एवं कॉपी वितरण",
            date = "Recent",
            category = NoticeCategory.EVENTS,
            summary = "State curriculum textbook sets issued free of cost to all enrolled students.",
            summaryHi = "बिहार राज्य पाठ्यपुस्तक निगम की पुस्तकें सभी नामांकित बच्चों को बांटी जा रही हैं।",
            details = "Complete sets of textbooks from Bihar State Textbook Publishing Corporation have arrived and are being distributed class-wise. Ensure regular attendance to receive study materials.",
            detailsHi = "कक्षावार पुस्तकें वितरित की जा रही हैं। नियमित उपस्थिति अनिवार्य है।",
            isUrgent = false
        ),
        SchoolNotice(
            id = "not-04",
            title = "Swachhata Pakhwada & Tree Plantation Drive",
            titleHi = "स्वच्छता पखवाड़ा एवं वृक्षारोपण अभियान",
            date = "Campus Event",
            category = NoticeCategory.EVENTS,
            summary = "Students participated in school campus cleanliness and planted saplings.",
            summaryHi = "परिसर में स्वच्छता अभियान चलाकर छायादार पौधे रोपे गए।",
            details = "Under the guidance of Vice Principal Mr. Janardan Ojha, teachers and students conducted a community hygiene rally and planted shaded trees along the school periphery.",
            detailsHi = "उप-प्रधानाध्यापक श्री जनार्दन ओझा के नेतृत्व में स्वच्छता रैली और पौधरोपण संपन्न हुआ।",
            isUrgent = false
        ),
        SchoolNotice(
            id = "not-05",
            title = "Periodic Evaluation & Assessment Schedule",
            titleHi = "मासिक एवं अर्द्धवार्षिक मूल्यांकन परीक्षा समय-सारणी",
            date = "Upcoming",
            category = NoticeCategory.EXAMS,
            summary = "Continuous and Comprehensive Evaluation (CCE) timetable for Class 1 to 8.",
            summaryHi = "सतत एवं व्यापक मूल्यांकन के अंतर्गत मासिक जांच परीक्षा कार्यक्रम।",
            details = "Continuous and Comprehensive Evaluation (CCE) tests will take place in the morning shift. Parents are encouraged to review class notebooks and ensure timely preparation.",
            detailsHi = "छात्र नियमित रूप से अपनी कॉपियों की पुनरावृत्ति करें एवं समय पर उपस्थित हों।",
            isUrgent = false
        )
    )

    val holidays = listOf(
        SchoolHoliday("Makar Sankranti", "मकर संक्रांति", "14 Jan", "1 Day", "State"),
        SchoolHoliday("Republic Day", "गणतंत्र दिवस (समारोह)", "26 Jan", "National", "National"),
        SchoolHoliday("Saraswati Puja / Basant Panchami", "सरस्वती पूजा / बसंत पंचमी", "Feb", "1 Day", "Cultural"),
        SchoolHoliday("Maha Shivratri", "महाशिवरात्रि", "Feb / Mar", "1 Day", "Religious"),
        SchoolHoliday("Bihar Diwas", "बिहार दिवस (उत्सव)", "22 Mar", "State", "State"),
        SchoolHoliday("Holi Festival", "होली अवकाश", "March", "2 Days", "Gazetted"),
        SchoolHoliday("Eid-ul-Fitr", "ईद-उल-फितर", "Apr", "1 Day", "Gazetted"),
        SchoolHoliday("Summer Vacation (Grishmavakash)", "ग्रीष्मावकाश", "June", "20 Days", "Vacation"),
        SchoolHoliday("Independence Day", "स्वतंत्रता दिवस (झंडोत्तोलन)", "15 Aug", "National", "National"),
        SchoolHoliday("Raksha Bandhan", "रक्षाबंधन", "August", "1 Day", "Gazetted"),
        SchoolHoliday("Janmashtami", "श्री कृष्ण जन्माष्टमी", "August", "1 Day", "Religious"),
        SchoolHoliday("Mahatma Gandhi Jayanti", "गांधी जयंती", "02 Oct", "National", "National"),
        SchoolHoliday("Durga Puja (Dussehra)", "दुर्गा पूजा / दशहरा", "October", "4 Days", "Gazetted"),
        SchoolHoliday("Deepawali & Goverdhan Puja", "दीपावली व गोवर्धन पूजा", "Oct / Nov", "2 Days", "Gazetted"),
        SchoolHoliday("Chhath Puja (Maha Parv of Bihar)", "महापर्व छठ पूजा", "November", "4 Days", "Bihar State Major"),
        SchoolHoliday("Christmas Day", "क्रिसमस अवकाश", "25 Dec", "1 Day", "Gazetted")
    )

    val faqs = listOf(
        FaqItem(
            question = "What is the minimum age for Class 1 admission?",
            questionHi = "वर्ग 1 में नामांकन के लिए न्यूनतम उम्र क्या है?",
            answer = "Under Right to Education (RTE) guidelines, a child must be 6 years of age at the start of the academic year.",
            answerHi = "शिक्षा का अधिकार (RTE) नियमानुसार नए सत्र के आरंभ में बच्चे की आयु 6 वर्ष पूर्ण होनी चाहिए।",
            category = "Admissions"
        ),
        FaqItem(
            question = "Whose bank account is required during admission?",
            questionHi = "नामांकन के समय किसका बैंक खाता देना होगा?",
            answer = "For students under 10 years of age, a parent's (mother/father) active bank account is accepted. For students aged 10 years or above, the child's own savings bank account is mandatory.",
            answerHi = "10 वर्ष से कम उम्र के बच्चों हेतु माता या पिता का बैंक खाता मान्य है। 10 वर्ष या उससे अधिक उम्र के बच्चों का स्वयं का खाता होना आवश्यक है।",
            category = "Documents"
        ),
        FaqItem(
            question = "Are textbooks and mid-day meals free?",
            questionHi = "क्या पाठ्यपुस्तकें और दोपहर का भोजन निःशुल्क है?",
            answer = "Yes! UMS Nehrua Kala is a government institution. Free textbooks, hot nutritious mid-day meals every school day, and uniform stipends are provided to all eligible students.",
            answerHi = "हाँ! यूएमएस नेहरुवा कला में सभी नामांकित बच्चों को निःशुल्क पुस्तकें, प्रतिदिन पौष्टिक मध्याह्न भोजन और पोशाक अनुदान दिया जाता है।",
            category = "Facilities"
        ),
        FaqItem(
            question = "How can I contact Vice Principal Mr. Janardan Ojha directly?",
            questionHi = "उप-प्रधानाध्यापक श्री जनार्दन ओझा से सीधे कैसे संपर्क करें?",
            answer = "You can call 9471471275 directly, or visit the school office in Nehrua Kala, Panchdeori, Gopalganj during working hours (9:00 AM to 3:30 PM).",
            answerHi = "आप सीधे 9471471275 पर फोन कर सकते हैं, या कार्यदिवसों में सुबह 9:00 से दोपहर 3:30 बजे तक विद्यालय कार्यालय में मिल सकते हैं।",
            category = "Contact"
        )
    )

    private val _submittedApplications = MutableStateFlow<List<FormalAdmissionApplication>>(
        listOf(
            FormalAdmissionApplication(
                applicationId = "UMS-NK-2025-0101",
                studentName = "Aman Kumar Ojha",
                fatherName = "Satish Ojha",
                motherName = "Geeta Devi",
                dob = "12/04/2018",
                gender = "Male",
                category = "General",
                seekingClass = "Class 1",
                phone = "9471471275",
                aadhaarNo = "XXXX-XXXX-8921",
                bankAccountNo = "3489101002341",
                bankIfsc = "SBIN0002931",
                villageAddress = "Nehrua Kala, Panchdeori, Gopalganj",
                previousSchool = "Direct Entry"
            )
        )
    )
    val submittedApplications: StateFlow<List<FormalAdmissionApplication>> = _submittedApplications.asStateFlow()

    fun saveFormalApplication(app: FormalAdmissionApplication) {
        _submittedApplications.value = listOf(app) + _submittedApplications.value
    }
}
