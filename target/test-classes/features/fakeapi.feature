Feature: Testes de API - Fake REST API

  ### ---------------- Books ---------------- ###

  @list-books
  Scenario: Listar todos os livros
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de livros
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books   |

  @book-details
  Scenario: Obter detalhes de um livro existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do livro específico
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books/  |

  @nonexistent-book
  Scenario: Obter detalhes de um livro inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido que o erro retornado tem o status code "<status>"
    Examples:
      | url                                     	 	 | endpoint    | status |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books/9999 | 404    |

  @create-valid-book
  Scenario Outline: Criar livros com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida "<endpoint>"
    Examples:
      | url                                    		   | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books   |

  @create-invalid-book
  Scenario Outline: Criar livros com dados inválidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>" com dados inválidos
    Then eu valido o erro retornado
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books   |

  @update-book
  Scenario Outline: Atualizar dados de um livro existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>" "<endpoint>"
    Examples:
      | url                                      | endpoint   | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books/1   | 200        |

  @delete-book
  Scenario: Excluir livro existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | url                                      | endpoint   | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Books/   | 200        |

  ### ---------------- Activities ---------------- ###

  @list-activities
  Scenario: Listar todas as atividades
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de atividades
    Examples:
      | url                                 		     | endpoint     |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Activities   |

  @activity-details
  Scenario: Obter detalhes de uma atividade existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados da atividade específica
    Examples:
      | url                                      		 | endpoint     |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/ |

  @create-valid-activity
  Scenario Outline: Criar atividades com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida "<endpoint>"
    Examples:
      | url                                          | endpoint     |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Activities   |

  @update-activity
  Scenario Outline: Atualizar dados de uma atividade existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>" "<endpoint>"
    Examples:
      | url                                      | endpoint     | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/1 | 200        |

  @delete-activity
  Scenario: Excluir atividade existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | url                                      | endpoint     | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Activities/ | 200        |

  ### ---------------- Authors ---------------- ###

  @list-authors
  Scenario: Listar todos os autores
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de autores
    Examples:
      | url                                      | endpoint   |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors    |

  @author-details
  Scenario: Obter detalhes de um autor existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do autor específico
    Examples:
      | url                                     		 | endpoint   |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/   |

  @authors-books
  Scenario: Listar autores de um livro específico
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido os autores relacionados ao livro
    Examples:
      | url                                      		 | endpoint                  |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/authors/books/1 |

  @create-valid-author
  Scenario Outline: Criar autores com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida "<endpoint>"
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors  |

  @update-author
  Scenario Outline: Atualizar dados de um autor existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>" "<endpoint>"
    Examples:
      | url                                      | endpoint   | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/1 | 200        |

  @delete-author
  Scenario: Excluir autor existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | url                                      | endpoint   | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Authors/1 | 200        |

  ### ---------------- Users ---------------- ###

  @list-users
  Scenario: Listar todos os usuários
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de usuários
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Users    |

  @user-details
  Scenario: Obter detalhes de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados do usuário específico
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Users/   |

  @create-valid-user
  Scenario Outline: Criar usuários com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida "<endpoint>"
    Examples:
      | url                                      | endpoint |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Users    |

  @update-user
  Scenario Outline: Atualizar dados de um usuário existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>" "<endpoint>"
    Examples:
      | url                                      | endpoint  | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Users/1   | 200        |

  @delete-user
  Scenario: Excluir usuário existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | url                                      | endpoint  | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /Users/1   | 200        |

  ### ---------------- CoverPhotos ---------------- ###

  @list-coverphotos
  Scenario: Listar todas as capas de livros
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a resposta com a lista completa de capas
    Examples:
      | url                                      | endpoint     |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos |

  @coverphoto-details
  Scenario: Obter detalhes de uma capa existente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>" e id
    Then eu valido os dados da capa específica
    Examples:
      | url                                      | endpoint       |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/  |

  @coverphoto-by-book
  Scenario: Obter capa de um livro específico
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then eu valido a imagem de capa do livro
    Examples:
      | url                                      | endpoint                    |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/books/covers/1 |

  @create-valid-coverphoto
  Scenario Outline: Criar capa com dados válidos
    Given que acesso a API "<url>"
    When realizo uma request POST para "<endpoint>"
    Then eu valido que a criação foi bem-sucedida "<endpoint>"
    Examples:
      | url                                      | endpoint     |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos |

  @update-coverphoto
  Scenario Outline: Atualizar dados de uma capa existente
    Given que acesso a API "<url>"
    When realizo uma request PUT para "<endpoint>" com novos dados
    Then eu valido que os dados foram atualizados corretamente com status "<statusCode>" "<endpoint>"
    Examples:
      | url                                      | endpoint       | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/1 | 200        |

  @delete-coverphoto
  Scenario: Excluir capa existente
    Given que acesso a API "<url>"
    When realizo uma request DELETE para "<endpoint>"
    Then eu valido o status de resposta "<statusCode>"
    Examples:
      | url                                      | endpoint       | statusCode |
      | https://fakerestapi.azurewebsites.net/api/v1 | /CoverPhotos/1 | 200        |
