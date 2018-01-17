package dk.pension_consulting.Advice_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.pension_consulting.R;

/**
 * Created by jonathanlarsen on 16/01/2018.
 */

public class Advice_Fragment extends android.support.v4.app.Fragment {

    private Toolbar toolbar;
    private ExpandableListView exlistView;
    private List<String> AdviceTitle;
    private HashMap<String, List<String>> AdviceText;


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.expandablelistview, container, false);


        exlistView = view.findViewById(R.id.expandebleList);

        toolbar = getActivity().findViewById(R.id.toolbar_actionbar);

        prep();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Gode råd");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        Advice_Adapter adapter = new Advice_Adapter(this.getActivity(), AdviceTitle, AdviceText);
        exlistView.setAdapter(adapter);


        return view;
    }

    public void prep () {
        AdviceTitle = new ArrayList<String>();
        AdviceText = new HashMap<String, List<String>>();


        AdviceTitle.add("Start Tidligt");
        AdviceTitle.add("Læg ikke alle dine æg i én kurv");
        AdviceTitle.add("Forsikringerne er vigtige");
        AdviceTitle.add("Hvis du har gæld");
        AdviceTitle.add("Du bør samle dine pensioner");
        AdviceTitle.add("Indbetal ekstra via din arbejdsgiver");
        AdviceTitle.add("Er du specialist i investeringer?");
        AdviceTitle.add("Begunstigelse");
        AdviceTitle.add("Hvor meget bør jeg indbetale?");
        AdviceTitle.add("Søg uvildig rådgivning");

        List<String> Advice1 = new ArrayList<>();
        Advice1.add("Det er en god ide at starte så tidligt som muligt med pensionsopsparingen. Med renters rente gør det en stor forskel for din opsparing som pensionist, hvis du har sparet op i 25 eller 35 år.\n" +
                "\n" +
                "Er du startet tidligt med pensionsopsparingen, får det mindre betydning, hvis du senere indbetaler mindre til pension i en periode, hvor du har andet at bruge pengene til f.eks. i forbindelse med barns fødsel eller huskøb.");

        List<String> Advice2 = new ArrayList<>();
        Advice2.add("Det er meget fornuftigt ikke kun at spare op på sin pensionsordning, men også at have andre former for opsparing som f.eks. fast ejendom. Betal derfor gerne lidt mindre ind på pensionsordningen og investér i stedet nogle penge på andre måder.");

        List<String> Advice3 = new ArrayList<>();
        Advice3.add("En pensionsordning indeholder i de fleste tilfælde forsikringer, som dækker dig og dine efterladte i tilfælde af sygdom og død. Det er meget vigtigt, at disse forsikringer altid er opdateret, så de svarer til dine behov.\n" +
                "\n" +
                "Din dækning ved mistet erhvervsevne skal justeres, hvis din løn har ændret sig meget.\n" +
                "\n" +
                "Din dækning ved død skal justeres, hvis du får forsørgerforpligtigelser, f.eks. hvis du bliver gift eller får børn.");

        List<String> Advice4 = new ArrayList<>();
        Advice4.add("Om du skal indfri lån i stedet for at spare mere op til pension afhænger af, hvor dyre lånene er. Jo dyrere lån, jo mere sandsynligt er det, at det bedre kan svare sig for dig at indfri lånet.\n" +
                "\n" +
                "Du kan som regel ikke forudsige hvor meget afkast, du vil få det kommende år på din pensionsordning. Det kan derfor være vanskeligt at gennemskue, om det bedre kan svare sig for dig at indfri et lån end at spare mere op til pension.\n" +
                "\n" +
                "Som tommelfingerregel kan man sige, at hvis de årlige omkostninger på lånet er over 5%, bør du overveje at indfri lånet i stedet for at spare mere op til pension.");

        List<String> Advice5 = new ArrayList<>();
        Advice5.add("Der er omkostninger forbundet med hver eneste pensionsordning, du har. Du kan derfor spare mange penge ved at samle dine pensioner.\n" +
                "\n" +
                "Der kan være rettigheder og garantier, som du mister, når du samler dine pensioner. Det er derfor ekstremt vigtigt, at du søger rådgivning, inden du beder om at få samlet dine pensioner.");

        List<String> Advice6 = new ArrayList<>();
        Advice6.add("Ønsker du at indbetale ekstra til din pensionsopsparing, kan du næsten altid få bedre vilkår, hvis du har mulighed for at gøre det via en evt. arbejdsgiver. Arbejdsgiverne har næsten altid forhandlet sig frem til bedre vilkår, end du selv kan opnå som privatperson.\n" +
                "\n" +
                "Indbetaler du via en arbejdsgiver, bliver det beløb, som du hver måned ønsker at indbetale ekstra til pensionsopsparing, automatisk trukket fra din løn og indbetalt til pensionsordningen. Herved har du fået skattefordelen med det samme.");

        List<String> Advice7 = new ArrayList<>();
        Advice7.add("Langt de fleste har ikke nogen særlig forstand på investering af deres pensionsopsparing. Er du som de fleste, bør du overlade investeringerne til eksperterne i dit pensionsselskab.\n" +
                "\n" +
                "Det eneste, som du altid bør forholde dig til, er hvilken risiko, du ønsker at løbe med dine investeringer, hvis du får valget.");

        List<String> Advice8 = new ArrayList<>();
        Advice8.add("De(n) begunstigede er de(n), som modtager udbetalingerne fra din pensionsordning i tilfælde af, at du dør. Er der flere begunstigede, deler de som udgangspunkt lige, men du kan også bestemme, hvordan fordelingen skal være mellem de begunstigede.\n" +
                "\n" +
                "Den mest almindelige begunstigelse er ”nærmeste pårørende”. Denne begunstigelse er som et lille columbusæg, der automatisk hele tiden tilpasser sig din familiemæssige situation. Denne begunstigelse gælder i de fleste tilfælde helt automatisk, hvis du ikke selv ændrer den.\n" +
                "\n" +
                "Det står i loven, hvem der er ”nærmeste pårørende”.\n" +
                "\n" +
                "Din ægtefælle / registreret partner er som udgangspunkt den, der er begunstiget.\n" +
                "Hvis du ikke efterlader en ægtefælle / registreret partner, så er det din evt. samlever, der er begunstiget. Dette gælder dog kun, hvis I lever i et ægteskabslignende forhold (kan gifte jer, hvis I vil) og begge disse to betingelser desuden er opfyldt:\n" +
                "I har haft fælles bopæl (folkeregister) i de sidste to år eller I har haft fælles bopæl i mindre end 2 år, men har et eller flere børn sammen inkl. ufødte børn, og\n" +
                "Begunstigelsen IKKE er indsat før den 1. januar 2008.\n" +
                "Hvis du ikke efterlader nogen af ovenstående, eller betingelserne i pkt. 2 ikke er opfyldt, er det dine livsarvinger (børn, børnebørn, oldebørn, mv.), der er begunstiget.\n" +
                "Efterlader du heller ikke nogen livsarvinger, gælder reglerne i arveloven, herunder reglerne om testamente.");

        List<String> Advice9 = new ArrayList<>();
        Advice9.add("Mange får indbetalt 10-15% til pension af deres løn, hvoraf arbejdsgiveren som regel betaler 2/3. Dette er et fornuftigt niveau, men du bør alligevel overveje at supplere med en ekstra frivillig indbetaling.\n" +
                "\n" +
                "Hvor meget du bør indbetale ekstra afhænger af din indtægt og dine udgifter – så enkelt kan det siges.\n" +
                "\n" +
                "Hvis du får børn eller køber hus/lejlighed, har du nok at bruge pengene til, og så bør du reducere dine pensionsindbetalinger, hvis du kan. Derfor er det godt, hvis du har indbetalt ekstra til pension forinden. Når du senere igen får råd, kan du begynde at indbetale mere til pension.\n" +
                "\n" +
                "Det er en god ide ikke kun at spare op til alderdommen i en pensionsordning, men f.eks. også i fast ejendom, mv.");

        List<String> Advice10= new ArrayList<>();
        Advice10.add("Det er vigtigt, at du søger rådgivning vedrørende dine forsikringsdækninger, investeringsvalg, samling af dine pensioner, begunstigelse, mv.\n" +
                "\n" +
                "Du kan let finde gratis rådgivning om dine pensioner, men det er sjældent den bedste!\n" +
                "\n" +
                "Derfor er det vigtigt at finde en pensionsrådgiver, som har de fornødne kompetencer, og som giver troværdige råd.");

        AdviceText.put(AdviceTitle.get(0), Advice1);
        AdviceText.put(AdviceTitle.get(1), Advice2);
        AdviceText.put(AdviceTitle.get(2), Advice3);
        AdviceText.put(AdviceTitle.get(3), Advice4);
        AdviceText.put(AdviceTitle.get(4), Advice5);
        AdviceText.put(AdviceTitle.get(5), Advice6);
        AdviceText.put(AdviceTitle.get(6), Advice7);
        AdviceText.put(AdviceTitle.get(7), Advice8);
        AdviceText.put(AdviceTitle.get(8), Advice9);
        AdviceText.put(AdviceTitle.get(9), Advice10);

    }
}
