Feature: Best Buy Products Feature

  @bestBuy @XRAY-0001
  Scenario: XRAY-0001 I get a list of products from Best Buy
    When I get all products from the store
    Then the response code should be 200
    And the response body field 'total' is not empty

  @bestBuy @XRAY-0002
  Scenario Outline: XRAY-0002 I get a product based on an ID
    When I get a product by id <id>
    Then the response code should be <response_code>
    And the response body field <field> is not empty

    Examples:
    |id            |response_code |field |
    |9575039       |200           |id    |
    |9575048       |200           |id    |
    |9575108       |200           |id    |

  @bestBuy @XRAY-0003
  Scenario Outline: XRAY-0003 I get a product based on an invalid ID
    When I get a product by id <id>
    Then the response code should be <response_code>

    Examples:
      |id          |response_code |
      |122345      |404           |
      |x           |404           |
      |0           |404           |

  @bestBuy @XRAY-0004
  Scenario Outline: XRAY-0004 POST a new product
    # please pass the XRAY ID of the test case as a parameter
    When I POST a new product for id <xray_id>
    Then the response code should be 201
    Examples:
      |xray_id        |
      |XRAY-0004_1    |
      |XRAY-0004_2    |
      |XRAY-0004_3    |

  @bestBuy @XRAY-0005
  Scenario Outline: XRAY-0005 POST an invalid product
    # please pass the XRAY ID of the test case as a parameter
    When I POST a new product for id <xray_id>
    Then the response code should be 400
    And the value in field errors equals <error_code>

    Examples:
      |xray_id        |error_code                                              |
      # Invalid Price
      |XRAY-0005_1    |['price' should be multiple of 0.01]                    |
      # Invalid description
      |XRAY-0005_2    |['description' should NOT be longer than 100 characters]|

  @bestBuy @XRAY-0006
  Scenario Outline: XRAY-0006 PUT a new product
    # please pass the product ID as a first parameter
    # and XRAY ID of the test case as a parameter
    When I PUT a new product <id> for id <xray_id>
    Then the response code should be 200
    # Check that the update occurred.
    When I get a product by id <id>
    Then the response code should be 200
    And the value in field name equals <value>
    Examples:
      |id          |xray_id        |value       |
      |9575039       |XRAY-0006_1    |PUT - Test New Product1|
      |9575048       |XRAY-0006_2    |PUT - Test New Product2|
      |9575108       |XRAY-0006_3    |PUT - Test New Product3|

  @bestBuy @XRAY-0007
  Scenario Outline: XRAY-0007 PUT a new invalid product
    # please pass the product ID as a first parameter
    # and XRAY ID of the test case as a parameter
    When I PUT a new product <id> for id <xray_id>
    Then the response code should be <response_code>
    And the value in field <field> equals <error_code>
    Examples:
      |id          |xray_id        |field         |response_code |error_code                                              |
      |123         |XRAY-0007_1    |message       |404           |No record found for id '123'                            |
      |9575048     |XRAY-0007_2    |errors        |400           |['price' should be multiple of 0.01]                    |
      |9575108     |XRAY-0007_3    |errors        |400           |['description' should NOT be longer than 100 characters]|

  @bestBuy @XRAY-0008
  Scenario: XRAY-0008 I delete a product by id
    # Since the API does not return the new product ID when POSTING a new entry,
    # so we can't generate our test data for the delete,
    # we will have to do a get and pick up the first ID from the list
    # and delete that one.
    When I delete a product by id
    Then the response code should be 200

  @bestBuy @XRAY-0009
  Scenario Outline: XRAY-0009 I delete a product by an invalid id
    When I delete a product by id <id>
    Then the response code should be <response_code>
    And the value in field <field> equals <error_code>

    Examples:
      |id          |response_code |field   |error_code                                              |
      |122345      |404           |message |No record found for id '122345'                            |
      |x           |404           |message |No record found for id 'x'                            |
      |0           |404           |message |No record found for id '0'                            |