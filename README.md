# FileConverterService
`FileConverterService` - программа для конвертации файлов из формата xml в json и из json в xml.

Файлы должны быть определенной структуры. В файле xml перечисляются ноутбуки и их характеристики. В файле json перечисляются бренды: у каждого есть название и список ноутбуков этого бренда. С конкретными примерами можно ознакомиться в разделе `src/test/resources`.

## Сборка проекта

В проекте используется система maven, поэтому для сборки проекта необходимо:
1. Установите последнюю версию maven с официального сайта: https://maven.apache.org/download.cgi.
2. Распакуйте скачанный архив в любую удобную папку.
3. После этого добавьте директорию maven/bin в переменную окружения PATH. 
4. После этого можно вызвать в консоли:
    ```bash
    mvn --version
    ```  
   Если команда выполнится, то все сделано правильно.
5. Откройте командную строку в корневой папке проекта и наберите:
    ```bash
    mvn clean package
    ```
После этого в каталоге `target` будет создан исполняемый jar файл.
   
   

## Запуск программы

После сборки проекта его можно запустить из консоли двумя способами:
1. Запуск с аргументами:
    ```bash
    java -jar имя_jar_файла имя_конвертируемого_файла имя_выходного_файла
    ```
    Таким способом рабочие файлы будут указаны прямо при запуске.
2. Запуск без аргументов (интерактивный режим):
    ```bash
    java -jar имя_jar_файла
    ```
   Будет запущен интерактивный режим, где имена файлов надо будет указывать по запросам.

   Учтите, что если в качестве создаваемого файла будет указан уже существующий, то его содержимое будет перезаписано!

Программа также может быть запущена через IDE. Аргументы можно вручную настроить через конфигурацию запуска проекта.
