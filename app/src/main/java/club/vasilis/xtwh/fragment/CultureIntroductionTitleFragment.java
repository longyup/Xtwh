package club.vasilis.xtwh.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.CultureIntroductionContentActivity;
import club.vasilis.xtwh.domain.CultureIntroduction;

/**
 * 首页
 */
public class CultureIntroductionTitleFragment extends Fragment {

    private ViewPager vp_main;
    private ArrayList<ImageView> imageViews;
    private TabLayout tab_culture_introduction;



    private List<CultureIntroduction> CultureMainTitle = new ArrayList<>();
    private List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
    private CultureIntroductionAdapter adapter;
    private RecyclerView cultureIntroductionTitleRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //垂直滚动
        View view = inflater.inflate(R.layout.culture_introduction_title_frag, container, false);
        cultureIntroductionTitleRecyclerView = view.findViewById(R.id.rv_culture_introduction_title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        cultureIntroductionTitleRecyclerView.setLayoutManager(layoutManager);
        adapter = new CultureIntroductionAdapter(getCultureIntroduction());
        cultureIntroductionTitleRecyclerView.setAdapter(adapter);

        vp_main = view.findViewById(R.id.vp_main);

        //在ViewPager的初始化之后发送消息
        mHandler.sendEmptyMessageDelayed(0, 1000*2);

        //准备数据
        int[] ids = new int[]{
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3
        };

        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(getContext());
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中去
            imageViews.add(imageView);



            //设置viewpage的适配器
            vp_main.setAdapter(new MyPagerAdapter());


        }

        //tablayout实现水平滚动
        tab_culture_introduction = view.findViewById(R.id.tab_culture_introduction);
        tab_culture_introduction.addTab(tab_culture_introduction.newTab().setText("文化遗产"));
        tab_culture_introduction.addTab(tab_culture_introduction.newTab().setText("特色美食"));
        tab_culture_introduction.addTab(tab_culture_introduction.newTab().setText("名人趣事"));
        tab_culture_introduction.addTab(tab_culture_introduction.newTab().setText("民俗风情"));
        tab_culture_introduction.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //添加选中Tab的逻辑
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() == "文化遗产") {
                    mCultureIntroductionList.clear();
                    adapter.notifyDataSetChanged();
                    adapter = new CultureIntroductionAdapter(getCultureIntroduction());
                    cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                }
                if (tab.getText() == "特色美食") {
                    mCultureIntroductionList.clear();
                    adapter.notifyDataSetChanged();
                    adapter = new CultureIntroductionAdapter(getCultureIntroduction2());
                    cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                }

                if (tab.getText() == "名人趣事") {
                    mCultureIntroductionList.clear();
                    adapter.notifyDataSetChanged();
                    adapter = new CultureIntroductionAdapter(getCultureIntroduction3());
                    cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                }

                if (tab.getText() == "民俗风情") {
                    mCultureIntroductionList.clear();
                    adapter.notifyDataSetChanged();
                    adapter = new CultureIntroductionAdapter(getCultureIntroduction4());
                    cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                }
            }
            //添加未选中Tab的逻辑
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //再次选中tab的逻辑
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    //CultureIntroduction的适配器
    class CultureIntroductionAdapter extends RecyclerView.Adapter<CultureIntroductionAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.culture_introduction_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(holder.getAdapterPosition());
                    CultureIntroductionContentActivity.actionStart(getActivity(), cultureIntroductions.getTitle(), cultureIntroductions.getContent());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(i);
            viewHolder.tv_culture_introduction_title.setText(cultureIntroductions.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCultureIntroductionList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_culture_introduction_title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_culture_introduction_title = itemView.findViewById(R.id.tv_culture_introduction_title);
            }
        }

        public CultureIntroductionAdapter(List<CultureIntroduction> CultureIntroductionList) {
            mCultureIntroductionList = CultureIntroductionList;
        }
    }

    //文化遗产数据
    private List<CultureIntroduction> getCultureIntroduction() {

        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            if(i == 1){
                cultureIntroduction.setTitle("古琴艺术（浙派古琴）");
                cultureIntroduction.setContent("    2003年11月，联合国教科文组织在巴黎宣布了第二批“人类口述和非物质遗产代表作”，中国古琴艺术与世界其他27个文化艺术表现形式获此殊荣。 古琴是中华民族最早的弹弦乐器，是中华传统文化之瑰宝。她以其历史久远，文献瀚浩、内涵丰富和影响深远为世人所珍视。湖北曾侯乙墓出土的实物距今有二千四百余年，唐宋以来历代都有古琴精品传世。存见南北朝至清代的琴谱百余种，琴曲达三千首，还有大量关于琴家、琴论、琴制、琴艺的文献，遗存之丰硕堪为中国乐器之最。古时，琴、棋、书、画并称，用以概括中华民族的传统文化。历代涌现出许多著名演奏家，他们是历史文化名人，代代传颂至今。隋唐时期古琴还传入东亚诸国，并为这些国家的传统文化所汲取和传承。近代又伴随着华人的足迹遍布世界各地，成为西方人心目中东方文化的象征。\n" +
                        "\n" +
                        "浙派是我国最古老的一个古琴流派，其操琴风格属于吴越系统，指法圆润，节奏紧凑，于2010年追加入“中国古琴艺术”。其创始人是南宋时期著名琴家郭沔，祖籍浙江永嘉，他用自己高超的艺术造诣、对琴乐的独特体会和对国家危亡的急切关注，创作了《潇湘水云》《泛沧浪》《秋鸿》等传世金曲，也为浙派琴艺的形成奠定了基石。");
            }
            if(i == 2){
                cultureIntroduction.setTitle("中国蚕桑丝织技艺");
                cultureIntroduction.setContent("    蚕桑丝织是中华民族认同的文化标识，五千年来，它对中国历史作出了重大贡献，并通过丝绸之路对人类文明产生了深远影响。为更好地保存保护祖先留下的这些珍贵的文化遗产，浙江、江苏、四川等三省作为蚕桑生产的主产区和蚕桑丝织文化的保护地，三省文化行政部门联合行动，以中国蚕桑丝织技艺为项目由中国丝绸博物馆向联合国教科文组织申报人类口头与非物质文化遗产名录。 中国蚕桑丝织包括:杭罗、绫绢、丝绵、蜀锦、宋锦等织造技艺及轧蚕花、扫蚕花地等丝绸生产习俗。\n" +
                        "\n" +
                        "2009年9月30日，在阿联酋首都阿布扎比召开的联合国教科文组织保护非物质文化遗产政府间委员会会议决定，“中国蚕桑丝织技艺” 入选《人类非物质文化遗产代表作名录》。");
            }
            if(i == 3){
                cultureIntroduction.setTitle("中国二十四节气");
                cultureIntroduction.setContent("    2016年11月30日，联合国教科文组织保护非物质文化遗产政府间委员会第11届常会通过审议，批准中国申报的“二十四节气”列入联合国教科文组织人类非物质文化遗产代表作名录。杭州市拱墅区“半山立夏习俗”作为“二十四节气”申报内容的重要组成部分，成为杭州文化走向世界的又一名片。");
            }
            if(i == 4){
                cultureIntroduction.setTitle("灯彩制作体验活动在非遗馆举行");
                cultureIntroduction.setContent("    灯彩是元宵节不可或缺的元素。2月17日，在桐庐东门码头非遗馆举办了一场趣味盎然的灯彩制作体验活动，吸引了全县20余名小朋友和他们的家长一同参加了体验活动。\n" +
                        "\n" +
                        "活动由2013年全国山花奖灯彩制作金奖获得者申屠飞东为小朋友们详细讲解了扎灯技艺特点，灯彩制作的步骤和注意点。并现场演示灯彩的实际制作过程。小朋友们跟着家长一起动手，递材料、粘胶带，忙得不亦乐乎。\n" +
                        "\n" +
                        "非遗馆举办此次灯彩体验活动，让小朋友们在扎架、裱糊、绘画等传统工艺的体验中，与灯彩来一次亲密的接触，感受文化遗产的隽永魅力，让越来越多的青少年认识非遗、参与非遗，加深他们对中华民族优秀传统文化的了解。\n" +
                        "\n" +
                        "    在桐庐东门码头非遗馆，2月19日，一场以“龙舞春江，福满桐庐”2019元宵非遗周为主题的传统活动如期举行。开展桐庐剪纸等非遗项目活态展演、手工技艺传统小吃展示展销、舞龙表演等传统民俗活动。");
            }
            if(i == 5){
                cultureIntroduction.setTitle("龙舞春江，福满桐庐");
                cultureIntroduction.setContent("    2月16日，由桐庐县文化和广电旅游体育局主办，桐庐县非遗保护中心承办的“龙舞春江，福满桐庐”2019年元宵非遗周系列活动在桐庐东门码头非遗馆热闹开启。\n" +
                        "\n" +
                        "非遗馆二楼，陆续参观的市民和正在进行的“非遗”课堂，让整个非遗馆“活”了起来。20余名孩子和家长一起走进“非遗”课堂，体验葫芦烙画，感受“非遗”魅力。课上，葫芦画传承人陈建红老师向大家介绍，在我国民间，葫芦素有“宝葫芦”的美誉，葫芦一直被视为吉祥物，以葫芦为题材的民间故事不胜枚举，在葫芦上刻画和装饰的艺术称为“葫艺”。了解了葫芦的文化内涵后，小朋友便跟着陈老师一起在“宝葫芦”上烙起了画。经过近三个小时的精心制作，原先的普通葫芦，都变成了一个个绚丽的葫芦画，还穿上了中国结，小朋友们对自己的作品爱不释手，家长们看着小朋友的作品更是连连称赞。非遗馆三楼戏剧小舞台上，正在上映的《春江花月夜》，不仅吸引了众多的老年朋友，还有不少年轻市民也前来观看，整个非遗馆人气满满。\n" +
                        "\n" +
                        "    今天的活动只是“龙舞春江，福满桐庐”2019年元宵非遗馆体验周系列活动中的一小部分，这个元宵桐庐非遗馆还有一系列活动等着大众参与。");
            }
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }


    //特色美食
    private List<CultureIntroduction> getCultureIntroduction2() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            if (i == 1){
                cultureIntroduction.setTitle("东坡肉");
                cultureIntroduction.setContent("    大家对于东坡肉非常熟悉了吧，相传北宋诗人苏东坡在杭州任地方官时所创制，这道菜薄皮嫩肉，色泽红亮，味醇汁浓，酥烂而形不碎，香糯而不腻口，在1956年被浙江省认定为36种杭州名菜之一。");
            }
            if (i == 2){
                cultureIntroduction.setTitle("蜂蜜莲藕配糯米");
                cultureIntroduction.setContent("    蜂蜜莲藕糯米是杭州一种当地的甜点，有莲藕酿成的糯米、桂花和糖制成，菜中使用的莲藕来自于西湖，这道菜完美的结合了脆嫩的莲藕、糯米和桂花，提供了独特的甜味和质地，杭州人非常的喜欢。");
            }
            if (i == 3){
                cultureIntroduction.setTitle("西湖醋鱼");
                cultureIntroduction.setContent("    这道菜是来到杭州必吃的一道美食，用的西湖鲲鱼作为原料，吃起来鱼肉鲜美，带有蟹味，鲜嫩酸甜，别具一番特色，相传发源自古时宋史两兄弟与宋嫂智斗贪官恶霸的故事，又叫叔嫂传珍。");
            }
            if (i == 4){
                cultureIntroduction.setTitle("龙井虾仁");
                cultureIntroduction.setContent("    这是来到杭州必吃的菜色之一，地方有名的特色菜，虾中带脆，而且有淡淡了龙井茶叶，而对于重口味的人可能有点不适应，这道菜属于清淡菜品。");
            }
            if (i == 5){
                cultureIntroduction.setTitle("西湖桂花藕粉");
                cultureIntroduction.setContent("    来到杭州看西湖，当然要品尝西湖出产的藕粉啊，半透明糊状的甜点，入口细腻爽滑，上面撒上桂花，吃一口带有清香，满嘴都是香气，十分的好吃，据说还有养胃的功效。\n" +
                        "\n");
            }
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    //名人趣事
    private List<CultureIntroduction> getCultureIntroduction3() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            if(i ==1){
                cultureIntroduction.setTitle("赵构爱喝宋嫂鱼羹");
                cultureIntroduction.setContent("    南宋孝宗淳熙六年，赵构一行游聚景园（今柳浪闻莺与清波公园）时吃了一碗宋五嫂的鱼羹。这宋五嫂原是东京汴州（开封）人，金兵入侵时南下到了杭州，烧的鱼羹带有浓浓的北宋家乡风味，勾起了赵构浓浓的思乡之情。\n" +
                        "\n" +
                        "赵构特别垂爱这道菜，不仅赏赐金银，还请她到后宫制作鱼羹，以便不时品尝。时人诗云：“一碗鱼羹值几钱？旧京遗制动天颜；时人倍价来争市，半买君思半买鲜。”");
            }
            if(i ==2){
                cultureIntroduction.setTitle("海瑞背纤吃到干菜鸭");
                cultureIntroduction.setContent("    皇太子坐龙船到新安江一带游春观景，下令让农村青年为龙船背纤。同行的淳安知县海瑞为了不影响农事，没有去抽调民工，而是亲自带领衙役替民工背纤。一农妇见状十分感动，就想炖只鸭子去慰劳下海瑞。可这鸭子刚好在换毛，一身细毛，很难拔尽。农妇灵机一动，抓出一把霉干菜与鸭子一同炖制，让人分不清哪是鸭毛、哪是干菜。\n" +
                        "\n" +
                        "这菜令海瑞感动得泪如珠下，和衙役们吃得津津有味，连皇太子都闻香打听是什么菜。从此，严州干菜鸭就出了名，鸭肉带着霉干菜的浓香，霉干菜则渗透了油润的鸭汁，酥烂可口，香味溢嘴，实在美味。");
            }
            if(i ==3){
                cultureIntroduction.setTitle("鲁班造香炉");
                cultureIntroduction.setContent("    巧匠鲁班带着妹妹来杭州务工，正巧碰上黑鱼精在西湖里花样游泳搞得全城乌烟瘴气，自恋的黑鱼精还想强娶鲁小妹做妻子，鲁班将宝石山的一座悬崖凿成香炉做嫁妆，巨型香炉把黑鱼精稳稳压在西湖底，只剩下三座葫芦状的炉脚留在西湖湖面。");
            }
            if(i ==4){
                cultureIntroduction.setTitle("康熙与云林禅寺");
                cultureIntroduction.setContent("    康熙皇帝下江南，来到了杭州。他在西湖四周到处游山玩水，吟诗题字，自称是个风雅的皇帝。 　一天，他要到灵隐来耍子了。灵隐寺里的老和尚得知消息，真是又惊又喜，连忙撞钟击鼓，把全寺三百多个和尚都召集拢来。和尚们披起崭新的袈裟，头顶檀香，手敲法器，嘴里念着“南无阿弥陀佛”，大家跟着老和尚，赶到一里路外的石莲亭，把康熙皇帝接到灵隐来。老和尚陪着康熙皇帝，在寺前寺后、山上山下游玩一番。康熙皇帝见到灵隐有高高的山峰，清清的泉水，山上长满绿荫荫的树，地下开遍红艳艳的花，真是一个好地方呵！他心里一高兴，就吩咐人在寺里摆酒用膳，想多耍子一会儿。 ");
            }
            if(i ==5){
                cultureIntroduction.setTitle("臭秦桧");
                cultureIntroduction.setContent("    西湖边有座岳坟，岳坟前跪着四个铁铸的人像，其中有两个就是当年出卖祖国、害死岳飞的秦桧夫妇。早先，杭州城里来了一个新上任的抚台，那人也姓秦，是秦桧的后代。抚台上任不久，便带手下人去逛西湖。他来到岳坟，看到自己的老祖宗跪在别人面前，忙用衣袖把脸遮住，倒退了出来。抚台回到衙门，坐不安，立不宁，便叫来师爷商量，想把铁像搬掉。师爷捋捋胡须，想了一想说：“如果明搬吧，老百姓一定不肯，说不定还会闹出事来。我看，不如派人在黑夜里把这对铁像丢进西湖里去。这样大的西湖，铁像沉到湖底，便是把水抽干了也难找到。”抚台连声称赞道：“妙！妙！”于是当夜便派人把铁像丢进湖里去了。");
            }
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    //民俗风情
    private List<CultureIntroduction> getCultureIntroduction4() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            if(i == 1){
                cultureIntroduction.setTitle("开茶节");
                cultureIntroduction.setContent("    西湖产茶历史悠久。西湖龙井始于宋、扬于明、盛于清，汇“色翠、香郁、味甘、形美”四绝于一身，在全国都是举世闻名的。集名山、名寺、名湖、名泉于一体，名贯古今、饮誉中外，与西湖同辉，是杭州城市品牌的巨大无形资产。\n" +
                        "　　每年在三月底、四月初在西湖龙井茶乡举行。\n" +
                        "\n" +
                        "　　宣传西湖龙井茶文化，展示西湖龙井茶传统特色，促进茶乡休闲旅游，推动西湖龙井茶品牌建设，增加茶区农民的收入。通过举办新闻发布会、西湖龙井茶传统技艺展示、文化宣传和文艺表演等活动，吸引更多的游客前来休闲观光，通过开茶节的开幕仪式宣布西湖龙井茶已进入了开采期。\n" +
                        "\n" +
                        "　　活动子项目\n" +
                        "\n" +
                        "　　炒茶王大赛、媒体茶乡游、茶文化创意大赛、全民品茶日活动、茶宴评选等。\n");
            }
            if(i == 2){
                cultureIntroduction.setTitle("听芦节");
                cultureIntroduction.setContent("　　一叶扁舟出没于芦洲之中，一场与秋雪邂逅的浪漫之旅。\n" +
                        "　　西溪听芦节在每年11月－12月进行，旧时，杭州西溪的名胜，除了清代“西湖十八景”之一的“西溪探梅”之外，就数“西溪芦雪”了，此一景观曾经闻名遐迩。西溪的芦花洁白轻盈，一经风吹，河渚两岸恍恍如有无数白头翁个个摇头而立。此时整个西溪宛如一副浓墨重彩的水乡秋景图，在这苍茫辽远、水波涟漪、秋雪飞舞的情景中，人们感受到的是浩渺、古寂、幽野之美。\n" +
                        "\n" +
                        "　　活动时间：每年11月下旬——12月中旬\n" +
                        "\n" +
                        "　　活动地点：西溪湿地公园内\n" +
                        "\n" +
                        "　　西溪进入11月以后，景区内的300多亩芦花全面泛白，风吹着芦絮摆动，宛如雪季一般。这个时候来西溪，不用远离城市，也不用\n" +
                        "\n" +
                        "　　来去匆忙。西溪的深秋与初冬，蒹葭隔水，霜涵镜中，芦荻团花，雪压蓬背，意境清冷。当这芦雪绽放，吹起这季节的风雪，你也会和郁达夫一样，到西溪的芦荡之中，迎着早晨微冷的晨雾与黄昏温暖的夕阳，就这么在西溪秋雪温柔的怀里，躺上一天，都忘了思考，忘了讲述，忘了行船，好让自己,静静欣赏西溪的美。");
            }
            if(i == 3){
                cultureIntroduction.setTitle("瑶山秋千");
                cultureIntroduction.setContent("　　“瑶山秋千”主要传播于淳安县的瑶山、光昌、富山和王阜。这一带位于淳安县北部，与临安市和安徽的歙县毗邻，距千岛湖风景区38公里，是浙江省山核桃之乡。是一种当地农民用来赶集或社庙活动中使用的游乐器械。由四根木柱做成一个可旋转的十字叉，每个叉口系一条布带，布带上坐一个小孩。当十字叉旋转起来时，布带上的四个小孩就轮番上下转动，小孩的感觉是一上一下，故称“秋千”，又因为是出在瑶山乡一带，故称“瑶山秋千”。\n" +
                        "　　“瑶山秋千”是融杂技、舞蹈和歌唱为一体的淳安民间民俗表演活动。秋千为一柏木制作的柱架，柱架上装有能移动的木轮十字架，十字架上另有四副小秋千。表演时，秋千由八名壮年男子扛抬行走，行进中伴有颇具地方特色的“三吹三打”。小秋千上依次坐着身穿红、绿、黄、蓝四色的四个小女孩，四个小女孩齐心协力使小秋千转动翻滚，当十字叉转动起来时，坐在叉口的小女孩就从地底下“转”到三米多高的空中去，给人一种惊险刺激感，同时架转人歌，四个小女孩统一舞动手中的羽毛绒扇，旋转手绢花。这样的表演具有惊险性、欢快性，加上表演时还配有乐队的伴奏，长长的唢呐、二胡、还有两个穿粉红衣服的小女孩抬着一面鼓，场面十分地欢快、热闹、壮观。“瑶山秋千”这次在秀水街庙会上的精彩亮相，也让在场的市民和游客都欢呼喝彩。\n" +
                        "\n" +
                        "　　“瑶山秋千”源起于南宋。据清《淳安县志》记，东汉间该地有一聪颖博学的人名方储，他精占卜，官迁太常，因含冤而自尽，被追赠尚书令。民间传说他善医药，故当地尊他为“方仙翁”。淳安的方姓者多为其后裔，因此纷纷为他建庙塑像，现在瑶山一带每年农历的正月初六和三月初三都要举行“方仙翁庙会”，期间要进行秋千、露台表演，以烘托庙会气氛。\n" +
                        "\n" +
                        "　　“瑶山秋千”作为淳安民族民间传统艺术精品，具有较高的价值，一是研究地域民俗文化价值。秋千虽形成于南宋时期，但通过对秋千相关资料的搜集整理及研究，可进一步加强对方氏历史文化渊源的研究。二是农产品品牌打造价值。瑶山、富山、王阜均为我县山核桃、茶叶、小竹笋等农副产品的主要产地。借助秋千活动这一平台，可以扩大农副产品的影响力和知名度，提升品牌效应。三是文化娱乐价值。瑶山秋千具有表演惊险性、演出欢快性、秋千与戏曲的融合性，表演时配有乐队伴奏，场面十分欢快、热闹，可以极大地丰富当地广大群众的业余文化生活。四是旅游开发价值。随着淳安乡村旅游事业的不断发展，秋千可作为旅游文化活动的一项重要内容，展示给游客。“瑶山秋千”的价值经过我县有关部门的重视与保护已逐步展现出其特有的民间艺术魅力。");
            }
            if(i == 4){
                cultureIntroduction.setTitle("淳安三脚戏");
                cultureIntroduction.setContent("    淳安民俗素尚歌舞，每到春节元宵，市井乡间均有演傩神戏和表演各种歌舞的习俗，跳竹马尤为盛行。清光绪年间，湖北黄梅采茶戏和江西赣东采茶戏传入浙西后，与民间歌舞跳竹马（竹马班）相结合，以采茶戏的剧目、曲调及表演形式，融合本地的民间音乐、地方语言及风俗习惯，由歌舞向戏曲演变，于清代末年逐渐形成浙西的地方小戏——三脚戏（通常是生、旦、丑三个脚色演一出小戏，故名），又叫睦剧，睦剧是全国少数地方剧种之一，流行于浙西山区。其表演特点自然、活泼、粗犷、朴实，以表演生活、生产动作为表现手法，生活气息和乡土风味浓郁。\n" +
                        "　　清光绪十六年（1890年），开化菖蒲乡宋村徐龙福（淳安人）最早开办“三脚戏班”。经徐龙福传艺，遂安、淳安一带有了一批三脚戏艺人。此后，三脚戏在淳安扎根、发展。前期多为半农半艺的业余班社，仍保留竹马班的形式，但增加了三脚戏剧目的演出。这种又跳竹马又演戏的班社，称“三脚戏竹马班”，一般有生、旦、丑等四五个演员和两三个敲锣鼓的人。后来，出现了三脚戏艺人组织的半职业性班社，不跳竹马而专门演戏。这种日趋职业化的戏曲班社，称“三脚戏常班”，农闲季节到外地农村草台、祠堂庙台演出。常班一般有七八个演员（增加了正生、正旦、老生、老旦等）和三四个敲锣鼓的。");
            }
            if(i == 5){
                cultureIntroduction.setTitle("九姓渔民水上婚礼\n");
                cultureIntroduction.setContent("　　“九姓渔民”又称“九姓渔户”，是对生活在新安江流域的一群有着特殊习俗的渔民的称呼，是我国古代的一类贱民。据《杭州市志·民情风俗篇》载，“九姓渔民”的起源有两种说法。\n" +
                        "　　一种说法是南宋末年，元兵南下，攻下临安（今杭州），一些不愿受蒙古人蹂躏的南宋遗民和部分士大夫便离开故土，漂泊水上，意为不愿再踏上已被蒙古人践踏过的土地。他们终年以打鱼为生，久而久之，便形成了浙西一带水上特有的一个相对封闭的社会群体。\n" +
                        "\n" +
                        "　　流传更广的一种说法是“九姓渔民”是明初陈友谅及其部将的后代。相传元朝末年，朱元璋与陈友谅为争夺天下，朱元璋打败了陈友谅，并俘虏了陈友谅的部将，将陈友谅的部属押解到浙江严州府（今建德市梅城镇），流放到新安江中，并贬为“民”，规定他们不得上岸居住、不准与岸上人通婚、不准读书应试、不准穿鞋上岸，而且官府有事还要应召服役。后来他们世世代代生活在水上，以打鱼、载客为生，很少与岸上人往来，数百年的水上生活形成了独特的生活习俗。\n" +
                        "\n");
            }
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    //vp_main适配器
    class MyPagerAdapter extends PagerAdapter {

        //返回数据的总个数
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 添加控件，添加内容
         * container ViewPager
         * position  要创建页面的位置
         */

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);

            return imageView;
        }

        /**
         * 判断是否为同一张图片
         * view     当前创建的视图
         * object   instantiateItem返回的结果值
         */

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //destroyItem（）是加入页面的时候，默认缓存三个，如不做处理，滑多了程序就会崩
        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

    }

    //自动刷新
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
            int count = myPagerAdapter.getCount();
            int index= vp_main.getCurrentItem();
            index=(index+1)%count;
            vp_main.setCurrentItem(index);    //收到消息后设置viewPager当前要显示的图片
            mHandler.sendEmptyMessageDelayed(0, 1000*2);    //第一个参数随便写；第二个参数表示每两秒刷新一次
        }
    };
}
