import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Hw4 {
    public static void main(String[] args) throws IOException {
        String str = "";
        ArrayList<String> familyName = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> surName = new ArrayList<>();
        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<Boolean> gender = new ArrayList<>();
        LinkedList<Integer> index = new LinkedList<>();

        try {
            try (FileWriter wr = new FileWriter("bd.sql")) {
                wr.append("Ильин Федор Ярославович 24 М\n");
                wr.append("Суханов Панкратий Наумович 56 М\n");
                wr.append("Копылов Нинель Еремеевич 45 М\n");
                wr.append("Артемьев Дональд Ефимович 12 М\n");
                wr.append("Исаков Юлий Рубенович 26 Ж\n");
                wr.append("Быкова Алла Мэлоровна 33 Ж\n");
                wr.append("Кудрявцева Екатерина Геласьевна 63 Ж\n");
                wr.append("Герасимова Юстина Ефимовна 18 Ж\n");
                wr.append("Белоусова Фанни Сергеевна 43 Ж\n");
                wr.append("Корнилова Лаура Богдановна 22 Ж\n");
                wr.append("Фомичёва Нора Антониновна 44 Ж\n");
                wr.append("Воробьёва Илена Леонидовна 37 Ж\n");
                wr.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            try (FileReader fr = new FileReader("bd.sql")) {
                while (fr.ready()) str += (char) fr.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String [] strArray = str.split("\n");
        System.out.println("Исходный список:");
        for (int i = 0; i < strArray.length; i++) {
            String [] strTmp = strArray[i].split(" ");
            System.out.printf("%s %s.%s. %s %s\n", strTmp[0], strTmp[1].toUpperCase().charAt(0), strTmp[2].toUpperCase().charAt(0), strTmp[3], strTmp[4]);

            familyName.add(strTmp[0]);
            name.add(strTmp[1]);
            surName.add(strTmp[2]);
            age.add(Integer.parseInt(strTmp[3]));
            gender.add(strTmp[4].equals("М")? false : true);
            index.add(i);
        }

        System.out.println("\nСписок отсортированный по возрасту:");
        sortIndexesByIntList(age, index);
        printFromLists(familyName, name, surName, age, gender, index);
    }

    private static void printFromLists(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3,
                                       ArrayList<Integer> list4, ArrayList<Boolean> list5, LinkedList<Integer> index) {

        for (Integer i : index) {
            String gender = (list5.get(i).equals(true) ? "Ж" : "М");
            System.out.printf("%s %s.%s. %s %s\n", list1.get(i), list2.get(i).toUpperCase().charAt(0),
                    list3.get(i).toUpperCase().charAt(0), list4.get(i), gender);
        }
    }

    private static void sortIndexesByIntList(ArrayList<Integer> arrayToSort, LinkedList<Integer> ind) {
        ArrayList<Integer> arr = new ArrayList<>(arrayToSort);

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int tmp = ind.get(j);
                    ind.set(j, ind.get(j + 1));
                    ind.set(j + 1, tmp);

                    tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);
                }
            }
        }
    }
}