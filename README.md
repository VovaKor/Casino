# Casino
В проекте для удобство в .gitignore был добавлен application.properties!
Создайе его в своих проектах и добавте так же в .gitignore

Последние содержимое файла до коммита с удалением 

# spring.thymeleaf.cache: false
# debug: true
# security.basic.enabled: false
# data base
spring.datasource.url = jdbc:mysql://localhost:3306/casino
spring.datasource.username = root
spring.datasource.password = dasska123

spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1
spring.jpa.hibernate.ddl-auto = update
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
