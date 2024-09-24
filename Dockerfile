# Используем базовый образ Amazon Corretto 17
FROM amazoncorretto:17

# Копируем все файлы проекта в директорию /app внутри контейнера
COPY . /app

# Устанавливаем рабочую директорию
WORKDIR /app

# Выполняем сборку проекта с пропуском тестов
RUN ./mvnw clean package -DskipTests

# Указываем команду для запуска приложения
CMD ["java", "-jar", "target/to-do-List.jar"]