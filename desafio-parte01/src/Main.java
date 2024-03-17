import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
                line = br.readLine();
            }

            List<Sale> listAno = list.stream().filter(s -> s.getYear() == 2016).sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice())).limit(5).toList();

            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
            listAno.forEach(System.out::println);

            Double totaltSale = list.stream()
                    .filter(s -> s.getSeller().equals("Logan"))
                    .filter(s -> s.getMonth()==1 || s.getMonth()==7)
                    .map(Sale::getTotal)
                    .reduce(0.0, Double::sum);

            System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = "+totaltSale);

        } catch (IOException e) {
            System.out.println("Error: " + path + " (O sistema não pode encontrar o arquivo especificado)");
        }
        sc.close();
    }
}