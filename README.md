# CoffeeMaker
A handy tool to generate universal code template.



**Step one. Configuration**

1. Config datasource properties
`src/main/resource/config.properties`

2. Config code generation rules
```java
Configuration configuration = new Configuration();
        configuration.setTableName("t_user")
            .setTablePrefix("")
            .setPackageName("com.workholiday")
            .setPagerPackageName("com.workholiday.base.core.page")
            .setWithPager(true)
            .setOutputPath("/Users/hunterzhao/tmp/output");
```


**Step two. Generation**

Launch CoffeeMaker via `main` method in CoffeeMakerLauncher.


**Step three. Enjoy :D**

Generate code template list:
- Entity file
- DAO file
- Mbatis mapper file
- Service file
- Service implementation file
- VO file
- Controller file



