package com.mahdi.recylcerviewitemclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewOnItemClick {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    private List<NovelsModel> novelsModels = new ArrayList<>();
    private List<NovelsModel> deletedNovels = new ArrayList<>();
    private List<NovelsModel> archivedNovels = new ArrayList<>();

    private int archivedCounter = 0;
    private int deletedCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // insert all data
        initNovelsModel();

        recyclerView = findViewById(R.id.main_rv);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(novelsModels, this);
        recyclerView.setAdapter(recyclerAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            final int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedNovels.add(position, novelsModels.get(position));
                    novelsModels.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);

                    String deletedNovelsName = deletedNovels.get(deletedCounter).getNovelNames();
                    deletedCounter++;
                    Snackbar.make(recyclerView, deletedNovelsName, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    novelsModels.add(position, deletedNovels.get(position));
                                    recyclerAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;

                case ItemTouchHelper.RIGHT:
                    archivedNovels.add(novelsModels.get(position));
                    novelsModels.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    String archivedNovelsName = archivedNovels.get(archivedCounter).getNovelNames();
                    archivedCounter++;
                    Snackbar.make(recyclerView, archivedNovelsName + "Archived.", Snackbar.LENGTH_LONG)
                            .setAction("View Archive", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(MainActivity.this, ArchivedActivity.class);
                                    Parcelable parcelable = Parcels.wrap(archivedNovels);
                                    intent.putExtra("archivedNovels", parcelable);
                                    startActivity(intent);

                                }
                            }).show();
                    break;
            }

        }
    };


    private void initNovelsModel() {

        novelsModels.add(new NovelsModel("في قلبي انثي عبرية",
                "خولة حمدي",
                "\n" +
                        "في قلب حارة اليهود في الجنوب التونسي تتشابك الأحداث حول المسلمة اليتيمة " +
                        "التي تربت بين أحضان عائلة يهودية، و بين ثنايا " +
                        "مدينة قانا العتيقة في الجنوب اللبناني تدخل بلبلة غير متوقعة في " +
                        "حياة ندى التي نشأت على اليهودية بعيدا عن " +
                        "والدها المسلم. تتتابع اللقاءات و الأحداث المثيرة حولهما لتخرج كلا " +
                        "منهما من حياة الرتابة و تسير بها إلى موعد مع القدر. (في قلبي أنثى عبرية) " +
                        "رواية مستوحاة من أحداث حقيقية في قالب روائي مشوق ", R.drawable.fe_qalbe_onsa));

        novelsModels.add(new NovelsModel("الفيل الازرق",
                "احمد مراد",
                "\n" +
                        "بعد خمس سنوات من العُزلة الاختيارية يستأنف د. يحيى عمله في مستشفى العباسية للصحّة النفسية، حيث يجد في انتظاره مفاجأة..\n" +
                        "\n" +
                        "في \"8 غرب\"، القسم الذي يقرّر مَصير مُرتكبي الجرائم، يُقابل صديقاً قديماً يحمل إليه ماضياً جاهد طويلاً لينساه، ويصبح مَصيره فجأة بين يدي يحيى..\n" +
                        "\n" +
                        "تعصِف المفاجآت بيحيى وتنقلب حياته رأسًا على عقب، ليصبح ما بدأ كمحاولة لاكتشاف حقيقة صديقه، رحلة مثيرة لاكتشاف نفسه.. أو ما تبقى منها..\n" +
                        "\n" +
                        "يأخذنا أحمد مراد في روايته الثالثة إلى كواليس عالم غريب قضى عامين في دراسة تفاصيله، رحلة مثيرة مستكشف فيها أعمق وأغرب خبايا النفس البشرية..",
                R.drawable.elfel_elasrq));

        novelsModels.add(new NovelsModel("يوتوبيا",
                "احمد خالد توفيق",
                "\n" +
                        "كيف ستكون مصر عام 2023؟\n" +
                        "لقد عزَلَ الأغنياء أنفسهم في (يوتوبيا) الساحل الشمالي تحت حراسة المارينز الأمريكيين، يتعاطون المخدرات " +
                        "ويمارسون المتع المحرمة إلى أقصاها، بينما ينسحق الفقراء خارجها ينهش بعضهم لحم بعض من أجل العيش، دونما كهرباء أو صرف صحي" +
                        " أو رعاية طبية من أي نوع. ولكن حين يتسلل الراوي وصديقته (جرمينال) خارج (يوتوبيا) بدافع الملل وبحثا عن (صيد بشري)\n" +
                        "مناسب يحدث ما يهدد الوضع المستقر بالانفجار.\n" +
                        "\n" +
                        "فيما يشبه هول علامات يوم القيامة، تدق هذه الرواية المثيرة ناقوس الخطر، تكاد تشك إذ " +
                        "تنهيها أهي بالفعل رواية متخيلة، أم إن كاتبها تسلل من المستقبل القريب لينقل لك هوله بحياد مذهل؟",
                R.drawable.youtubea
        ));

        novelsModels.add(new NovelsModel("قصر الكلام",
                "جلال عامر",
                "\n" +
                        "«عابر سبيل.. ابن الحارة المصرية، ليس لى صاحب.. لذلك كما ظهرت فجأة سوف أختفي فجأة.. فحاول تفتكرني..»\n" +
                        "\n" +
                        "بهذه الكلمات، اختار الأستاذ جلال عامر أن يعرف نفسه دومًا. و بها، كانت روح هذا الكتاب...",
                R.drawable.oser_elklam1
        ));

        novelsModels.add(new NovelsModel("وا إسلاماه", "علي احمد بالكثير",
                "\n" +
                        "هذه قصة تجلو صفحة رائعة من صفحات التاريخ المصري فى عهد من اخصب عهوده واحلفها بالحوادث الكبرى والعبر الجلى . " +
                        "يطل منها القارئ على المجتمع الإسلامي في أهم بلاده من نهر السند إلى نهر النيل وهو يستيقظ من سباته الطويل على صليل سيوف " +
                        "المغيرين عليه من تتار الشرق وصليبيى الغرب، فيهب للكفاح والدفاع عن أنفس ما عنده من تراث الدين والدنيا.\n" +
                        "\n" +
                        "ويشاء الله أن تحمل مصر لواء الزعامة في هذا الجهاد الكبير، فتحمى تراث " +
                        "الإسلام المجيد بيومين من أيامها عظيمين كلاهما له ما بعده: يوم الصليبيين في فارسكور، ويوم التتار فى عين جالوت.\n" +
                        "\n" +
                        "وبطلها الملك المظفر قطز يضرب بنزاهته وعدلة، وشجاعته وحزمه، وصبره وعزمه ، ووفائه " +
                        "وتضحيته، وحنكته السياسية وكفايته الإدراية، وإخلاصه فى خدمة الدين والوطن مثلا عاليا للحاكم المصلح ن والرجل الكامل.\n" +
                        "\n" +
                        "وهى بعد شهادة ناطقة بأن فى هذا الشعب الوديع " +
                        "الذي يسكن على ضفاف النيل قوة كامنة إذا وجدت من يحسن استثارتها والانتفاع بها أتت بالعجائب وقامت بالمعجزات.",
                R.drawable.w_islamah));

        novelsModels.add(new NovelsModel("هيبتا", "محمد صادق",
                "\n" +
                        "تأخذنا رواية (هيبتا) إلي ذلك العالم الذي أهلكه الجميع بحثا ..\n" +
                        "ذلك العالم الذي رغم تكرار قصصه ورواياته إلا أن الجميع فيه يقع في نفس الأخطاء، ويعيد نفس الأحداث، و يتألم نفس الألم ..\n" +
                        "خلال محاضرة مدتها ست ساعات يأخذنا (أسامة) المحاضر إلى حالات نادرة ..\n" +
                        "ورغم ندرتها لن تستطيع إلا أن تجد نفسك فيها ..\n" +
                        "في عالم الحب والأمل والألم .. من خلال حالات نعيشهم و نفهم منهم تلك المراحل السبع التي لخصت كل القواعد .. قواعد الـ\"هيبتا\" ",
                R.drawable.hebta));

        novelsModels.add(new NovelsModel("تراب الماس", "احمد مراد",
                "\n" +
                        "\"للمرة الثانية بعد \"فيرتيجو\" يتّخذ أحمد مراد من الجريمة خلفية تكشف بأسلوب " +
                        "مشوّق كواليس المجتمع و الفساد المستشري وسط كل طبقاته.. وهو بذلك يؤّكد قواعد النوع الروائي الذي أصبح رائدًا له\"..\n" +
                        "صنع الله إبراهيم\n" +
                        "لم يكن \"طه\" سوى مندوب دعاية طبية في شركة أدوية؛ حياة باهتة رتيبة، بدلة و كرافتة و حقيبة جلدية و لسان لبق يستميل أعتى الأطباء لأدويته\n" +
                        "وفي البيت يعيش وحيدًا مع أبيه القعيد\n" +
                        "كان ذلك قبل أن تقع جريمة قتل غامضة تتركه خلفها و قد تبدّل عالمه.. للأبد\n" +
                        "تتحول حياته إلى جزيرة من الأسرار، يبدأ اكتشافها في دفتر عتيق يعثر عليه مصادفة، و يجد معه أداة رهيبة لها فعل السحر\n" +
                        "عندها يبدأ \"طه\" ومعه الضابط \"وليد سلطان\" في تتبع قاتل لا يملكان دليلا ضده\n" +
                        "سنقرأ هنا كيف تتحول هذه الجريمة إلى سلسلة من عمليات القتل. وكيف " +
                        "يصبح القتل بابًا يكشف لنا عالما من الفساد، وسطوة السلطة التي تمتد لأجيال في تتابع مثير لا يؤكد أبدا أن \"طه\" سيصل إلى نهايته",
                R.drawable.trab_elmas
        ));

        novelsModels.add(new NovelsModel("قصاصات قابلة للحرق", "احمد خالد توفيق",
                "\n" +
                        "عندما تقلب في أوراقك القديمة تجد الكثير من الهراء.. كثيرًا من الكلام الفارغ الذي لا رأس له ولا ذيل وبعضه ينم عن سخف وسذاجة" +
                        " بالغة أو تفائل مضحك أو ثقة بالنفس غير مبررة أو شعور بالضعة لا داعي له، مصداقًا لقصيدة قديمة لنزار قباني يقول فيها:\n" +
                        "أتلو رسائلنا فتضحكني... أمثل هذا السخف قد كنا؟\n" +
                        "عندما تقلب في أوراقك القديمة تجد الكثير من الهراء...\n" +
                        "لكنك كذلك تجد بقايا أفكار ولمحات من خواطر فيها بعض اللحم.\n" +
                        "عندها يخطر لك أن هذه القصاصات تصلح لشيء ما.",
                R.drawable.ksasat_qabla_llhrq
        ));

        novelsModels.add(new NovelsModel("انتحار فاشل", "احمد جمال الدين رمضان",
                "\n" +
                        "كتاب \"انتحار فاشل\" عبارة عن مجموعة قصصية متنوعة تجمع ما بين " +
                        "القصة الساخرة، الاجتماعية، الرعب ، وحتى القصص المبنية على أحداث حقيقية، يلعب \"أحمد رمضان\" فى هذه " +
                        "المجموعة ببراعة شديدة على وتر الأفكار المختلفة والنهايات المفاجئة، حتى فى القصص التى تبدو أفكارها مألوفة " +
                        "تأتى المعالجة شديدة التميز والنهاية مخالفة تماماً للتوقعات تضيف أبعاداً أكثر عمقاً للعمل.\n" +
                        "أحمد جمال الدين رمضان - ❰ له مجموعة من المؤلفات أبرزها ❞ العصبي ❝ ❞ إنتحار فاشل ❝ ❞ شقة 47 ❝ ❱",
                R.drawable.entehar_fashl
        ));

        novelsModels.add(new NovelsModel("زغازيغ", "احمد خالد توفيق", "\n" +
                "من متجر علاج الاكتئاب، أردنا أن نأتيك بدعابات ظريفة .. أو نكات مضحكة " +
                ".. أو طرائف مسلية .. أو مٌَلح مقرظة، فلم نجد للأسف .. لهذا ابتعنا لك نصف كيلو زغازيغ... ",
                R.drawable.sag
        ));


    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ShowMoreActivity.class);

        intent.putExtra("book_images", novelsModels.get(position).getNovelImage());

        intent.putExtra("book_intros", novelsModels.get(position).getNovelIntros());

        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

        novelsModels.remove(position);
        recyclerAdapter.notifyItemRemoved(position);
    }
}
