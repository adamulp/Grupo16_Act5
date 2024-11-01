Feature: Calculadora

  Scenario Outline: Realizar operaciones matemáticas básicas
    Given that tengo un calculadora
    When sumo <numero1> y <numero2>
    Then el resultado debe ser <resultado>

    Examples:
      | numero1 | numero2 | resultado |
      | 5.5    | 4.5     | 10.0      |
      | 3.2    | -1.2    | 2.0       |
      | 0      | 0       | 0         |

  Scenario Outline: Probar resta
    Given that tengo un calculadora
    When resto <numero1> de <numero2>
    Then el resultado debe ser <resultado>

    Examples:
      | numero1 | numero2 | resultado |
      | 10.0   | 4.0     | 6.0       |
      | 5.5    | 3.5     | 2.0       |
      | -3.0   | -1.0    | -2.0      |

  Scenario: Inicializar calculadora y mostrar mensaje de bienvenida
    @BeforeClass
    Given that inicializo el calculadora
    Then debería ver el mensaje "Bienvenido al calculadora"

  Scenario: Mensaje antes de cada operación
    @Before
    Given that voy a realizar una operación
    When uso System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName())
    Then debería ver el nombre del método en consola

  Scenario: Mensaje después de cada operación
    @After
    Given that he terminado de probar una operación
    Then debería ver el mensaje "Prueba finalizada, campos en 0"

  Scenario: Mensaje al finalizar todas las operaciones
    @AfterClass
    Given that todas las operaciones han finalizado
    Then debería ver el mensaje "La operación ha finalizado"

  Scenario: Probar división por cero
    @Test(expected = ArithmeticException.class)
    Given that tengo un calculadora
    When intento dividir <numerador> entre <denominador>
    Then debería lanzar una ArithmeticException

    Examples:
      | numerador | denominador |
      | 10        | 0           |
      | 5         | 0           |

  Scenario Outline: Prueba parametrizada para suma
    @RunWith(Parameterized.class)
    Given that tengo un calculadora
    When sumo <numero1> y <numero2>
    Then el resultado debe ser <resultado>

    Examples:
      | numero1 | numero2 | resultado |
      | 8       | 7       | 15        |
      | 2       | 0       | 2         |
      | 20      | -10     | 10        |
      | -1      | -9      | -10       |

  Scenario: Test Suite "miSweetSuite"
    @RunWith(Suite.class)
    @Suite.SuiteClasses({
      parameterTest.class,
      calculadoraTest.class,
      divisionTest.class
    })
    Then debería ejecutar todos los tests en orden
