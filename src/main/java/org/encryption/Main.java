package org.encryption;

public class Main {
    public static void main(String[] args) {
        String text = "Язык исходного текста русский или английский по выбору исполнителя";

        SimpleTable simpleTable1 = new SimpleTable(text);

        String code = simpleTable1.encode();
        String decode = simpleTable1.decode(simpleTable1.encode(), text);


        System.out.println("Прямоугольная таблица\n" + simpleTable1);
        System.out.println("Шифр\n" + code);
        System.out.println("Исходный текст\n" + decode);

    }
}

/*
* текст
* генерация размеров таблицы по размеру текста
*
* чтение текста по таблице
*
* чтение таблицы по столбцам
*
* */

/*
* table
* fillTable
* readColumns()
* readRows()
* */

/*
* перебор размеров
* table()
* readRow()
* сравнить до длины последнего блока
* */

/*
* table(length)*/


/*
* теряется текст в конце
* */