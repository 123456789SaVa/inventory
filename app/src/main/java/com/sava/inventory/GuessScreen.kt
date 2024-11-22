package com.sava.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GuessScreen() {
    Column(Modifier.padding(16.dp)) {
        var textFieldText by rememberSaveable { mutableStateOf("") }
        var result by rememberSaveable { mutableStateOf("") }
        val manNamesList = listOf(
            "Александр", "Саша", "Алексей", "Леша", "Андрей", "Антон", "Арсений", "Арс", "Артем", "Тёма", "Богдан", "Борис",
            "Вадим", "Валентин", "Валера", "Валерий", "Василий", "Вася", "Виктор", "Витя", "Виталий", "Виталик", "Владимир", "Влад",
            "Владислав", "Владик", "Всеволод", "Вова", "Георгий", "Жора", "Григорий", "Гриша", "Давид", "Даниил", "Даня", "Денис",
            "Ден", "Дмитрий", "Дима", "Евгений", "Женя", "Егор", "Иван", "Ваня", "Игорь", "Кирилл", "Кир", "Константин", "Костя",
            "Леонид", "Лёня", "Максим", "Макс", "Матвей", "Миша", "Михаил", "Никита", "Николай", "Коля", "Олег", "Павел", "Паша",
            "Петр", "Петя", "Роман", "Рома", "Сергей", "Сережа", "Станислав", "Стас", "Степан", "Степа", "Тимофей", "Тима",
            "Юрий", "Юра", "Ярослав", "Ярик", "Анатолий", "Толя", "Вячеслав", "Слава", "Лев", "Лео", "Марсель", "Мирон", "Мирослав",
            "Руслан", "Русик", "Тимур", "Тима", "Альберт", "Эмиль", "Артур", "Ратмир", "Глеб", "Савелий", "Сава", "Марк", "Клим",
            "Адам", "Федор", "Федя", "Геннадий", "Гена", "Рустам", "Рустик", "Эрик", "Кристофер", "Радмир", "Алан", "Роберт",
            "Роб", "Назар", "Амир", "Эльдар", "Лука", "Дамир", "Илья", "Семен", "Арсен", "Лионель", "Тарас", "Милан", "Ян",
            "Елисей", "Ренат", "Викентий", "Радик", "Давлат", "Майкл", "Рон", "Тигран", "Ждан", "Серафим", "Оскар", "Ари", "Кир",
            "Айдар", "Эмин", "Эдгар", "Муса", "Ларс", "Стивен", "Гарри", "Патрик", "Оскар", "Эд", "Дамир", "Филипп", "Филя",
            "Азамат", "Юлиан", "Ян", "Султан", "Артемий", "Артемка", "Тимофей", "Сергей", "Роман", "Арсений", "Марс", "Радмир",
            "Натан", "Альфред", "Амос", "Маркус", "Гордей", "Мирослав", "Омар", "Диего", "Том", "Тео", "Харитон", "Фадей", "Ибрагим",
            "Лео", "Рафаэль", "Бен", "Дан", "Ильяс", "Карим", "Батыр", "Бехруз", "Адиль", "Юсуф", "Самир", "Жорик", "Алик",
            "Бек", "Жасур", "Нур", "Тимур", "Салим", "Омар", "Ислам", "Айдар", "Руслан", "Женя", "Платон", "Вениамин", "Алихан",
            "Эдгар", "Вильям", "Рем", "Владимир", "Валера", "Тамерлан", "Аристарх", "Батыр", "Юлиан", "Станимир", "Генрих",
            "Густав", "Ричард", "Эрик", "Жорик", "Эльбрус", "Саид", "Тамир", "Эмин", "Амир", "Рамзан", "Женя", "Ислам", "Леон",
            "Феликс", "Джозеф", "Ильдар", "Арнольд", "Касим", "Борислав", "Глеб", "Али", "Роберт", "Борис", "Донат", "Лев",
            "Камиль", "Лавр", "Фарид", "Габриэль", "Казбек", "Эрик", "Роберт", "Жерар", "Арчи", "Владислав", "Леон", "Назар",
            "Серго", "Октай", "Макар", "Ален", "Эд", "Мир", "Илья", "Самсон", "Стефан", "Альмир", "Рафаил", "Ларри", "Габриэль",
            "Василь", "Эрнест", "Эрик", "Рафаэль", "Женя", "Арнольд", "Диего", "Мариан", "Лаврентий", "Жан", "Тигран", "Теймур",
            "Артем", "Саша", "Виктор", "Игорь", "Феликс", "Руслан", "Ибрагим", "Камиль", "Джамал", "Гриша", "Азам", "Динар",
            "Сергей", "Тамерлан", "Аркадий", "Слава", "Шамиль", "Леон", "Амир", "Муса", "Арслан", "Фарид", "Давид", "Эмиль",
            "Эрик", "Артур", "Ренат", "Лео", "Эльдар", "Глеб", "Тимур", "Денис", "Арман", "Роман", "Александр", "Семён", "Максим",
            "Ярослав", "Артём", "Тимофей", "Виталий", "Михаил", "Данил", "Пётр", "Валерий", "Илья", "Кирилл", "Семен", "Павел",
            "Евгений", "Стас", "Антон", "Олег", "Николай", "Дмитрий", "Игорь", "Марк", "Федор", "Лев", "Альберт", "Тарас"
        ).map { it.lowercase() }
        val femaleNamesList = listOf(
            "Александра", "Саша", "Алена", "Алиса", "Алла", "Алина", "Алёна", "Альбина", "Анастасия", "Настя", "Анжелика",
            "Анна", "Аня", "Анфиса", "Арина", "Валентина", "Валя", "Валерия", "Лера", "Варвара", "Варя", "Василиса", "Вика",


        "Виктория", "Виталина", "Галина", "Галя", "Дарья", "Даша", "Диана", "Ева", "Евгения", "Женя", "Екатерина", "Катя",
        "Елена", "Лена", "Елизавета", "Лиза", "Есения", "Жанна", "Зарина", "Злата", "Зоя", "Иванна", "Илона", "Инга",
        "Инна", "Ирина", "Ира", "Камилла", "Карина", "Каролина", "Кира", "Кристина", "Ксения", "Ксюша", "Лариса", "Лара",
        "Лейла", "Лидия", "Лида", "Лилия", "Лиля", "Любовь", "Люба", "Людмила", "Люда", "Майя", "Маргарита", "Рита",
        "Марина", "Мария", "Маша", "Марфа", "Марьяна", "Мелания", "Мила", "Милана", "Надежда", "Надя", "Наталья", "Наташа",
        "Нелли", "Ника", "Нина", "Оксана", "Оля", "Ольга", "Полина", "Поля", "Раиса", "Регина", "Рената", "Римма", "Рита",
        "Роза", "Светлана", "Света", "Снежана", "София", "Софья", "Соня", "Таисия", "Тая", "Тамара", "Тома", "Татьяна",
        "Таня", "Ульяна", "Фаина", "Христина", "Эвелина", "Эльвира", "Юлия", "Юля", "Яна", "Ярослава", "Сабина", "Саида",
        "Сафия", "Самира", "Лилия", "Далила", "Влада", "Эльза", "Элеонора", "Вера", "Сара", "Нонна", "Сирена", "Лейсан",
        "Малика", "Майя", "Мира", "Ника", "Олеся", "Лея", "Клара", "Феруза", "Диляра", "Фатима", "Люция", "Илона",
        "Айгуль", "Селена", "Сиенна", "Жаклин", "Аделина", "Амира", "Радмила", "Евдокия", "Альмира", "Айна", "Карима",
        "Тамина", "Талина", "Юнона", "Ида", "Лора", "Анна-Мария", "Марта", "Антонина", "Лукерья", "Гелла", "Луиза",
        "Диана", "Аделия", "Аврора", "Камалия", "Венера", "Азиза", "Сабрина", "Эвелина", "Майя", "Анна-Лиза", "Софина",
        "Мари", "Мира", "Ксения", "Снежана", "Роксана", "Мадина", "Шахназ", "Фируза", "Гуля", "Татьяна", "Малика",
        "Индира", "Соня", "Табита", "Эмилия", "Эльза", "Элина", "Симона", "Милена", "Жанна", "Радмила", "Виолетта",
        "Габриэлла", "Жозефина", "Карина", "Диана", "Марфа", "Василина", "Олеся", "Захра", "Катарина", "Нора", "Тамина",
        "Алисия", "Марина", "Мерседес", "Магдалина", "Снежана", "Эсмира", "Джанна", "Мариэтта", "Лана", "Далия", "Дина",
        "Зейнаб", "Сафина", "Нейла", "Тереза", "Тиана", "Сания", "Амели", "Джессика", "Фарида", "Сабина", "Айлин",
        "Айгуль", "Самира", "Лаура", "Эльмира", "Яна", "Алия", "Амира", "Элина", "Сафия", "Диана", "Сильвия", "Аманда",
        "Изабелла", "Марта", "Евгения", "Айза", "Сюзанна", "Тамара", "Роксана", "Мирослава", "Сюзанна", "Ксения", "Алсу",
        "Екатерина", "Снежана", "Флора", "Вивиан", "Альфия", "Амалия", "Диля", "Лейла", "Ирма", "Лиза", "Серена", "Айяна",
        "Фарида", "Тина", "Гульнара", "Дариана", "Жаннет", "Мириам", "Иванна", "Нелли", "Алсу", "Сания", "Гюзель", "Алёна",
        "Галина", "Дарина", "Тамара", "Лола", "Юлиана", "Мия", "Тахмина", "Рафида", "Анжела", "Эльмира", "Розалия",
        "Алия", "Женя", "Зоя", "Алёна", "Юлия", "Валентина", "Натали", "Сабина", "Дания", "Каролина", "Дана", "Малика",
        "Амелия", "Таня", "Олеся", "Зара", "Элина", "Евгения", "Вероника", "Татьяна", "Лада", "Илона", "Оксана", "Маргарита",
        "Эмма", "Римма", "Камила", "Зинаида", "Кристина", "Алёна", "Ксения", "Аида", "Руфина", "Елизавета", "Жанна", "София"
        ).map { it.lowercase() }

        //аналогично добавить список животных
        //список марок машин
        //марок видеокарт
        //бренды

        result = if (femaleNamesList.contains(textFieldText.lowercase())) "Это женское имя"
        else if (manNamesList.contains(textFieldText.lowercase())) "Это мужское имя"
        else if (textFieldText.isNotBlank()) "Ты что-то написал"
        else "Пустота"



        TextField(textFieldText, { textFieldText = it })
        Spacer(Modifier.height(16.dp))
        Text(result, fontSize = 28.sp)
        Spacer(Modifier.height(16.dp))
        Text("Длина строки: ${textFieldText.length}", fontSize = 28.sp)
    }
}

val GUESS_SCREEN = "Guess Screen"