import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void calculationOfResults(int theNumberOfPersons, float allPrice) {

        float result = allPrice / theNumberOfPersons; // итог на каждую персону
        String valMessage = "", resultString = String.format("%.2f", result); //преобразуем итог в строку
        int index = resultString.indexOf(","); //позиция запятой
        boolean oneSymvol;
        char symvolOne = '0', symvolTwo = '0';
        char[] symvolsArray;

        symvolsArray = resultString.substring(0, index).toCharArray(); //получим массив символов из результата строки, возьмем только целую часть

        if (symvolsArray.length > 1) { //если целая часть состоит более чем из одной цифры
            oneSymvol = false;
            symvolTwo = symvolsArray[symvolsArray.length - 1]; //нас интересуют только две последние цифры целой части
            symvolOne = symvolsArray[symvolsArray.length - 2];
        } else { //если целая часть состоит из одной цифры
            oneSymvol = true;
            symvolOne = symvolsArray[symvolsArray.length - 1];
        }

        if (oneSymvol) {
            switch (symvolOne) {
                case '0':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    valMessage = "Рублей";
                    break;

                case '1':
                    valMessage = "Рубль";
                    break;

                case '2':
                case '3':
                case '4':
                    valMessage = "Рубля";
                    break;
            }
        } else {

            if ((symvolOne == '0') && (symvolTwo == '0') ||
                    (symvolOne == '0') && (symvolTwo == '5') ||
                    (symvolOne == '0') && (symvolTwo == '6') ||
                    (symvolOne == '0') && (symvolTwo == '7') ||
                    (symvolOne == '0') && (symvolTwo == '8') ||
                    (symvolOne == '0') && (symvolTwo == '9') ||
                    (symvolOne == '1') && (symvolTwo == '0') ||
                    (symvolOne == '1') && (symvolTwo == '1') ||
                    (symvolOne == '1') && (symvolTwo == '2') ||
                    (symvolOne == '1') && (symvolTwo == '3') ||
                    (symvolOne == '1') && (symvolTwo == '4') ||
                    (symvolOne == '1') && (symvolTwo == '5') ||
                    (symvolOne == '1') && (symvolTwo == '6') ||
                    (symvolOne == '1') && (symvolTwo == '7') ||
                    (symvolOne == '1') && (symvolTwo == '8') ||
                    (symvolOne == '1') && (symvolTwo == '9') ||
                    (symvolOne == '2') && (symvolTwo == '0')
            ) {
                valMessage = "Рублей";
            } else if (((symvolOne != '0') && (symvolOne != '1')) && ((symvolTwo == '2') || (symvolTwo == '3') || (symvolTwo == '4'))) {
                valMessage = "Рубля";
            } else if (((symvolOne != '0') && (symvolOne != '1')) && (symvolTwo == '1')) {
                valMessage = "Рубль";
            } else {
                valMessage = "Рублей";
            }
        }

        String tempString = String.format("\nКаждый (каждая) из %d персон должны заплатить по %.2f ", theNumberOfPersons, result);
        System.out.println(tempString + valMessage);
    }

    public static HashMap<String, Float> enterProducts() {
        HashMap<String, Float> listOfProducts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Float productPrice = 0f;

        while (true) {
            System.out.println("Введите наименование товара: ");
            String product = scanner.next();

            if (product.equalsIgnoreCase("завершить")) {
                return listOfProducts;
            }

            System.out.println("Введите цену " + product + ": ");

            try {
                productPrice = scanner.nextFloat();
            }catch (InputMismatchException e){
                productPrice = -1f; //присвоим значение, которое приведет к ошибке
                scanner.nextLine(); //пропустим прошлое значение
            }

            if(productPrice > 0) {
                listOfProducts.put(product, productPrice);
                System.out.println("Товар: " + product + " успешно добавлен в калькулятор");
            }else {
                System.out.println("Для товара: " + product + " введена не корректная цена, ввод отменен");
            }
        }
    }

    public static int enteringTheNumberOfPersons() {
        int theNumberOfPersons;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число персон: ");

        while (true) {
            try {
                theNumberOfPersons = scanner.nextInt();
            }catch(InputMismatchException e){
                theNumberOfPersons = 0; //присвоим значение, которое приведет к ошибке
                scanner.nextLine(); //пропустим введенное ранее значение
            }

            if (theNumberOfPersons <= 1) {
                System.out.println("Ошибка: введено не корректное количество персон. Введите число значением > 1");
            } else {
                return theNumberOfPersons;
            }
        }
    }

    public static void main(String[] args) {

        int theNumberOfPersons = enteringTheNumberOfPersons();
        HashMap<String, Float> listOfProducts = enterProducts();

        if (listOfProducts.size() == 0) {
            System.out.println("Нет товаров для расчета. Программа завершена.");
        } else {
            System.out.println("\nТовары для расчета:\n");
            float allPrice = 0;

            for (HashMap.Entry<String, Float> product : listOfProducts.entrySet()) {
                String productName = product.getKey();
                float productPrice = product.getValue();
                allPrice = allPrice + productPrice;

                String message = "%s, \t\t\t\t\tцена: %.2f р.";
                System.out.println(String.format(message, productName, productPrice));

            }
            calculationOfResults(theNumberOfPersons, allPrice);
        }
    }
}