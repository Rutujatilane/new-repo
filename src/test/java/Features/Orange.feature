Feature: Test the Orange HRM application

 Scenario: validate login functionality
    Given user open a browser
    When user enters valid username and valid password
    Then user click on login button
    
    
  Scenario: validate Home Page functionality
    Then user validte home page title
    And user validate home page url
    And user validate home page logo
    
    Scenario Outline: validate PIM Page functionality
    Given user click on PIM link
    When user click on add employee button
    And user enter firstName as "<firstname>" , middleName as "<middlename>" , lastName as "<lastname>" and capture the employee id
    Then user click on save button
    When user select gender as "<gender>"
    Then user click on save button
    When user enter the employee id
    Then user click on search button
    And user delete employee and confirm delete
    
     Examples: 
      | firstname | middlename | lastname  | gender |
      | Rutuja    | Bacharam   | tilane    | Female |
      | Ravi      | Ramesh     | Patil     | Male   |
    
    Examples: 
      | firstname | middlename | lastname  | gender |
      | Gaytri    |   Satish   | Mohite    | Female |
      | Raj       |   Rajiv    | shinde    | Male   |
      
      
    Scenario: validate logout functionality
    When user click on profile icon
    Then user click on logout
    