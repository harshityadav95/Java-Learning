# Optionals

## The need for Optional <a id="b1af"></a>

Before diving into the usage of _Optional_, let us understand what issue it promises to resolve. In any Java application, it is a common pattern to use method chaining to de-reference object properties. This often leads to the _NullPointerException_ whenever there is a null object reference.

```text
String countryCode = person.getAddress().getCountry().getCountryCode();
```

In the above code snippet, there could be a _NullPointerException_ in any of the _getXX\(\)_ invocations. To avoid _NullPointerException,_ this code needs to do tons of null checks as shown below:-

```text
if(Objects.nonNull(person)){
    Address address = person.getAddress();
    if(Objects.nonNull(address)){
        Country country = address.getCountry();
        if(Objects.nonNull(country)){
            String countryCode = country.getCountryCode();
        }
    }
}
```

The above code snippet does too many null checks. _Optional_ let us rewrite this code without worrying about _NullPointerExceptions_.

## Creating Optional <a id="d81d"></a>

O_ptional&lt;T&gt;_ class is part of _java.util_ package and it is a _value-based_ class. An optional instance can have either of the two states — it can have a reference to an instance of type T or empty. Following are the few noteworthy points about _Optional_ instances:-

* The _equals_, _hashcode,_ and _toString methods_ are implemented based on the state
* An _Optional_ instance is final and immutable. However, the reference it wraps can be mutable
* The optional class has no public constructor and expected to be accessed through the provided static factory methods

As discussed, an optional instance can only be created through the factory methods provided in the _Optional_ class. Following are the three factory methods:-

```text
Optional.empty();Optional.of(T value);Optional.ofNullable(T value);
```

### Use empty\(\) to create an instance <a id="2311"></a>

You can create an empty instance of _Optional_ by using the _empty\(\)_ method. An empty instance wraps _null._ Hence, accessing this instance will throw _NoSuchElementException_.

```text
@Rule
public ExpectedException expectedException = ExpectedException.none();@Test
public void whenEmptyOptionalThenExpectNoSuchElementExceptionOnGet(){
    expectedException.expect(NoSuchElementException.class);
    Optional<String> optional = Optional.empty();
    optional.get();
}
```

### Use of\(\) to create an instance <a id="179c"></a>

You can create an _Optional_ instance that can contain a value by using the _of\(\)_ method. This method provides us the flexibility to create an optional with null _\_or with a non-null reference. Following is the code snippet to create an optional with the \_null_ reference. Note that the below code snippet throws _NullPointerException_ as the _person_ is referenced to null.

```text
@Test
public void whenNullOptionalExpectNullPointerException(){
    Person person = null;
    expectedException.expect(NullPointerException.class);
    Optional<Person> user = Optional.of(person);
}
```

We can create a non-null _Optional_ instance in the following way:-

```text
@Test
public void whenNonNullOptionalThenOk(){
    Optional<String> user = Optional.of("John");
    assertThat(user.get(), is("John"));
}
```

### Use ofNullable\(\) to create an instance <a id="3e40"></a>

Creating instances with the _of\(\)_ method throws _NullPointerException_ if the wrapped reference is null. If the reference type can possibly be both null or non-null types, it is safe to use the _ofNullable\(\)_ method_._ This method returns an empty Optional instance if the reference contains a null value.

Following code snippet shows the usage of the _ofNullable\(\)_ method with a null reference:-

```text
@Test
public void whenOfNullableExpectEither(){
    Person person = null;
    Optional<Person> optional = Optional.ofNullable(person);
    assertThat(optional, is(Optional.empty()));
}
```

Following code snippet shows the usage of the _ofNullable\(\)_ method with a non-null reference:-

```text
@Test
public void whenOptionIfPresentThenOk(){
    Person person = new Person("John", "1456345609");
    Optional<Person> optional = Optional.ofNullable(person);
    assertThat(optional.isPresent(), is(true));
}
```

There are Optional classes for primitives to represent int, long and double primitive types.

```text
OptionalInt
OptionalLong
OptionalDouble
```

## Accessing Optional <a id="c433"></a>

Inthis section, we will talk about how to access the state of an _Optional_ instance.

### Access optional with get\(\) <a id="5efd"></a>

The simplest and common way to access by using the get\(\) method. It is a common practice is to test the Optional instance with the _isPresent\(\)_ method before calling the get\(\) method. As otherwise, there will be a _NoSuchElementException_ in the _get\(\)_ method call.

This is shown as below:

```text
@Test
public void whenNonEmptyObjectThenGet(){
    Person person = new Person("Somnath", "76543210");
    Optional<Person> optionalPerson = Optional.ofNullable(person);
    assertThat(optionalPerson.isPresent(), is(true));
    assertThat(optionalPerson.get(), is(person));
}
```

Another way to verify the presence of a value in the Optional instance is to use the _ifPresent\(\)_ method as shown below. In this scenario, assert is not invoked as the optional instance is empty.

```text
@Test
public void whenOptionalIfPresentThenNotOk(){
    Optional<Person> optional = Optional.ofNullable(null);
    optional.ifPresent(p -> assertThat(p.getName(), is("John")));
}
```

### Return default value with orElse\(\) <a id="0636"></a>

We have seen invoking get\(\) on empty instances throws an exception. There are instances when we don’t want this and instead would like to return a default value instead. Optional class API provides _orElse\(\)_ method to achieve this. In the below code snippet, we are returning a default instance if the wrapped reference is null.

```text
@Test
public void whenEmptyObjectThenReturnDefault(){
    Person person1 = null;
    Person person2 = new Person("Somnath", "76543210");
    Person optionalPerson = Optional.ofNullable(person1).orElse(person2);
    assertThat(optionalPerson, is(person2));
}
```

However, if the wrapper instance is a non-null instance, then _orElse\(\)_ will not be invoked.

```text
@Test
public void whenNonEmptyObjectThenOk(){
    Person person1 = new Person("John", "76543290");;
    Person person2 = new Person("Somnath", "76543210");
    Person optionalPerson = Optional.ofNullable(person1).orElse(person2);
    assertThat(optionalPerson, is(person1));
}
```

### Access optional with orElseGet\(\) <a id="fc75"></a>

With the _orElse\(\)_ method, we are bound to return a default instance of the wrapped type. The _orElseGet\(\)_ method lets us execute a _Supplier_ interface and return the result if the wrapped type is null.

```text
@Test
public void whenNonEmptyObjectThenOrElseGet(){
    Person person1 = new Person("John", "76543290");;
    Person optionalPerson = Optional.ofNullable(person1).orElseGet(() -> new Person("Somnath", "76543210"));
    assertThat(optionalPerson, is(person1));
}
```

This method appears to be similar to _orElse\(\)._ However, the difference is that _orElseGet\(\)_ is only executed if the wrapper instance is null. For non-null references, the Supplier instance is not executed.

### Throwing an Exception with orElseThrow\(\) <a id="62a9"></a>

The _orElse\(\)_ and _orElseGet\(\)_ let us return some default instance if the wrapped instance is _null_. However, there are scenarios where we would like to throw a specific exception type to indicate the non-availability of value. The _orElseThrow\(\)_ method let us do that as shown below:-

```text
@Rule
public ExpectedException expectedException = ExpectedException.none();@Test
public void whenEmptyObjectThenOrElseThrow(){
    expectedException.expect(RuntimeException.class);
    Person person1 = null;
    Person optionalPerson = Optional.ofNullable(person1).orElseThrow(RuntimeException::new);
}
```

In the above code snippet, a RuntimeException will be thrown as the wrapped person1 instance is null

## Transform Optional value <a id="87cb"></a>

Inthe previous section, we have seen to retrieve reference value from an _Optional_ instance. In this section, we will talk about transforming an Optional instance value with _map\(\)_ and _flatMap\(\)_ methods.

### Transform with map\(\) <a id="bceb"></a>

The _map\(\)_ method can be used to retrieve the Optional instance reference and transform. For instance, in the following example, the wrapped person object is retrieved and the associated name is transformed into the upper case.

```text
@Test
public void whenOptionalAndMapThenOk(){
    Person person1 = new Person("John", "876543009");
    String result = Optional.ofNullable(person1).map( p -> p.getName().toUpperCase()).orElse("Unknown");
    assertThat(result, is("JOHN"));
}
```

### Transform with flatMap\(\) <a id="c86d"></a>

The usage of _flatMap\(\)_ is comparatively complex than the _map\(\)_ method as it is used to flatten an Optional instance. In the below example, the _getProfession\(\)_ method returns an Optional instance.

```text
@Test
public void whenFlatMapThenOk(){
    Person person = new Person("Terry", "8760654311");
    person.setProfession("Journalist");
    String profession = Optional.ofNullable(person).flatMap(p -> p.getProfession()).orElse("Unknown");
    assertThat(profession, is("Journalist"));
}
```

There are other scenarios. where _flatmap\(\)_ method can be used effectively. For example, a getter method could return an Optional instance and the returned object’s getter method returns another Optional instance and this chain can be of any length.

Let us explain this with an example. We have a _Person_ class that returns an optional field named _Address_. An address returns an optional _Country_. A Country returns an optional CountryCode.

**Person.Java**

```text
public class Person {

    private String name;
    private String contactNo;
    private String profession;
    private Address address;

    public Person(String name, String contactNo) {
        System.out.println("Invoked Person constructor");
        this.name = name;
        this.contactNo = contactNo;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }// Other code snippets
```

**Address.java**

```text
public class Address {

    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }

    public Address(Country country) {
        this.country = country;
    }
}
```

**Country.java**

```text
public class Country {

    private String countryCode;

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public Optional<String> getCountryCode() {
        return Optional.ofNullable(countryCode);
    }
}
```

The following code snippet shows how to use _flatMap\(\)_ to flatten the Optional instance and to access the wrapped optional instance. At the end of the chain, we are accessing the country code string value.

```text
@Test
public void whenChainingThenOk(){
    Country newZealand = new Country("NZ");
    Address address = new Address(newZealand);
    Person person = new Person("Ross", "98700987");
    person.setAddress(address);

    String countryCode =  Optional.of(person)
            .flatMap(Person::getAddress)
            .flatMap(Address::getCountry)
            .flatMap(Country::getCountryCode)
            .orElse("UnKnown");

    assertThat(countryCode, is("NZ"));
}
```

> Traditionally, the standard JavaBeans style encourages to have generate getter and setter based on the property. Returning an Optional in the getter method violates this standard. However, this approach is popular in various ORM tools.

## Java 9 Additions <a id="f4b2"></a>

Asdiscussed earlier, the _Optional_ class was introduced in Java 8. In Java 9, this class was enhanced to support the following three more methods.

```text
or(Supplier<? extends Optional<? extends T>> supplier)ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)stream()
```

### Using or <a id="a371"></a>

The usage of _or\(\)_ method is similar to what _orElse\(\)_ and _orElseGet\(\)_ method does. However, _or\(\)_ method let us return an Optional instance that is produced by a _Supplier_.

```text
@Test
public void whenUseOrThenOk(){
    Person person = null;
    Person optionalPerson = Optional.ofNullable(person)
            .or(() -> Optional.of(new Person("default", "9898"))).get();
    assertThat(optionalPerson.getContactNo(), is("9898"));
}
```

### Using ifPresentOrElse <a id="1338"></a>

The _ifPresentOrElse\(\)_ method takes two arguments. One is a _Consumer_ instance and the other one is a _Runnable_ instance. The _Consumer_ instance is executed if the Optional instance contains some value, otherwise _Runnable_ instance is executed. This is shown as below:

```text
@Test
public void whenUseIfPresentOrElseThenOk(){
    Person person = null;
    Optional.ofNullable(person)
            .ifPresentOrElse(p -> System.out.println(p.getName()), () -> System.out.println("Runnable"));

}
```

### Using Stream <a id="d020"></a>

The _stream\(\)_ method allows us to transform an Optional instance to a Stream. Thus, let us use the features provided in the Stream API. If the Optional instance is empty, then the returned Stream will be an empty stream. Otherwise, the stream will contain the wrapped value.

```text
@Test
public void whenOptionalStreamThenCollect(){
    Person person = new Person("Ross", "8765430091");
    List<String> name = Optional.ofNullable(person)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());

    assertThat(name, hasItem("Ross"));
}
```

## Things to Consider <a id="d769"></a>

The following are the few important points to be noted while using _Optional:-_

* Optional instances are not _Serializable._ For this reason, it is intended to be used as a method return type, instead of a field in a class. However, there are libraries which allow us to do this
* Optional should not be used as a constructor parameter
* Optional is better used to build Fluent APIs

## Conclusion <a id="0a74"></a>

Optional is undoubtedly a powerful feature addition in Java 8. It not only let us avoid redundant null checks but also allow us to build fluent and readable APIs. Effective usage of this API will certainly empower a developer to write clean and less error-prone code.

The source code for this project could be found in [this GitHub ](https://github.com/musibs/java-optionals)Repository.

