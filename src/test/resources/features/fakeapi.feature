Feature: Testes de API - Fake REST API

  ### ---------------- Books ---------------- ###

  @list-books @all
  Scenario: Listar todos os livros
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de livros
    Examples:
      | tag    | url                                      | endpoint   |
      | @get1  | https://fakerestapi.azurewebsites.net/api/v1 | /Books     |

  @book-details @all
  Scenario: Obter detalhes de um livro existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do livro específico
    Examples:
      | tag    | url                                      | endpoint   |
      | @get2  | https://fakerestapi.azurewebsites.net/api/v1 | /Books/    |

  @nonexistent-book @all
  Scenario: Obter detalhes de um livro inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que o erro retornado tem o status code "<status>"
    Examples:
      | tag    | url                                      | endpoint    | status |
      | @get3  | https://fakerestapi.azurewebsites.net/api/v1 | /Books/9999 | 404    |

  @create-valid-book @all
  Scenario Outline: Criar livros com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida
    Examples:
      | tag    | url                                      | endpoint |
      | @post1 | https://fakerestapi.azurewebsites.net/api/v1 | /Books   |

  @create-invalid-book @all
  Scenario Outline: Criar livros com dados inválidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com dados inválidos
    Then eu valido o erro retornado
    Examples:
      | tag    | url                                      | endpoint |
      | @post2 | https://fakerestapi.azurewebsites.net/api/v1 | /Books   |

  @update-book @all
  Scenario Outline: Atualizar dados de um livro existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint   | statusCode |
      | @put1  | https://fakerestapi.azurewebsites.net/api/v1 | /Books/1   | 200        |

  @delete-book @all
  Scenario: Excluir livro existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint   | statusCode |
      | @del1  | https://fakerestapi.azurewebsites.net/api/v1 | /Books/1   | 200        |

  ### ---------------- Activities ---------------- ###

  @list-activities @all
  Scenario: Listar todas as atividades
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de atividades
    Examples:
      | tag    | url                                      | endpoint     |
      | @get4  | https://fakerestapi.azurewebsites.net/api/v1 | /Activities   |

  @activity-details @all
  Scenario: Obter detalhes de uma atividade existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados da atividade específica
    Examples:
      | tag    | url                                      | endpoint     |
      | @get5  | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/ |

  @create-valid-activity @all
  Scenario Outline: Criar atividades com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida
    Examples:
      | tag    | url                                      | endpoint     |
      | @post3 | https://fakerestapi.azurewebsites.net/api/v1 | /Activities   |

  @update-activity @all
  Scenario Outline: Atualizar dados de uma atividade existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint     | statusCode |
      | @put2  | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/1 | 200        |

  @delete-activity @all
  Scenario: Excluir atividade existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint     | statusCode |
      | @del2  | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/1 | 200        |

  ### ---------------- Authors ---------------- ###

  @list-authors @all
  Scenario: Listar todos os autores
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de autores
    Examples:
      | tag    | url                                      | endpoint   |
      | @get6  | https://fakerestapi.azurewebsites.net/api/v1 | /Authors    |

  @author-details @all
  Scenario: Obter detalhes de um autor existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do autor específico
    Examples:
      | tag    | url                                      | endpoint   |
      | @get7  | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/   |

  @authors-books @all
  Scenario: Listar autores de um livro específico
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido os autores relacionados ao livro
    Examples:
      | tag    | url                                      | endpoint                  |
      | @get8  | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/authors/books/1 |

  @create-valid-author @all
  Scenario Outline: Criar autores com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida
    Examples:
      | tag    | url                                      | endpoint |
      | @post4 | https://fakerestapi.azurewebsites.net/api/v1 | /Authors  |

  @update-author @all
  Scenario Outline: Atualizar dados de um autor existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint   | statusCode |
      | @put3  | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/1 | 200        |

  @delete-author @all
  Scenario: Excluir autor existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint   | statusCode |
      | @del3  | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/1 | 200        |

  ### ---------------- Users ---------------- ###

  @list-users @all
  Scenario: Listar todos os usuários
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de usuários
    Examples:
      | tag    | url                                      | endpoint |
      | @get9  | https://fakerestapi.azurewebsites.net/api/v1 | /Users    |

  @user-details @all
  Scenario: Obter detalhes de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do usuário específico
    Examples:
      | tag    | url                                      | endpoint |
      | @get10 | https://fakerestapi.azurewebsites.net/api/v1 | /Users/   |

  @create-valid-user @all
  Scenario Outline: Criar usuários com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida
    Examples:
      | tag    | url                                      | endpoint |
      | @post5 | https://fakerestapi.azurewebsites.net/api/v1 | /Users    |

  @update-user @all
  Scenario Outline: Atualizar dados de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint  | statusCode |
      | @put4  | https://fakerestapi.azurewebsites.net/api/v1 | /Users/1   | 200        |

  @delete-user @all
  Scenario: Excluir usuário existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint  | statusCode |
      | @del4  | https://fakerestapi.azurewebsites.net/api/v1 | /Users/1   | 200        |

  ### ---------------- CoverPhotos ---------------- ###

  @list-coverphotos @all
  Scenario: Listar todas as capas de livros
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de capas
    Examples:
      | tag    | url                                      | endpoint      |
      | @get11 | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos   |

  @coverphoto-details @all
  Scenario: Obter detalhes de uma capa existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados da capa específica
    Examples:
      | tag    | url                                      | endpoint        |
      | @get12 | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/   |

  @coverphoto-by-book @all
  Scenario: Obter capa de um livro específico
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a imagem de capa do livro
    Examples:
      | tag    | url                                      | endpoint                    |
      | @get13 | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/books/covers/1 |

  @create-valid-coverphoto @all
  Scenario Outline: Criar capa com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida
    Examples:
      | tag    | url                                      | endpoint     |
      | @post6 | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos |

  @update-coverphoto @all
  Scenario Outline: Atualizar dados de uma capa existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint       | statusCode |
      | @put5  | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/1 | 200        |

  @delete-coverphoto @all
  Scenario: Excluir capa existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | tag    | url                                      | endpoint       | statusCode |
      | @del5  | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/1 | 200        |
