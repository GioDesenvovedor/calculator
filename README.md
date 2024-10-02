# Calculator

Este é um projeto de calculadora desenvolvido para Android utilizando a arquitetura MVVM, Room, Coroutines, e com suporte a Snackbars. A calculadora possui duas telas, uma para a entrada dos valores e outra para exibição dos registros de operações.

## Funcionalidades

- Realiza operações matemáticas básicas.
- Armazena o histórico das operações no banco de dados local.
- Exibe mensagens utilizando `Snackbar` para feedback visual ao usuário.
- Uso da arquitetura MVVM (Model-View-ViewModel) para separação de responsabilidades.
- Persistência de dados utilizando Room e Coroutines para operações assíncronas.

## Tecnologias Utilizadas

- **Linguagem:** Kotlin
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Banco de dados:** Room Database
- **Programação Assíncrona:** Coroutines
- **UI:** Android View Binding, RecyclerView
- **Feedback Visual:** Snackbar
- **Dependências:**
  - Room Database (com suporte a `Kapt`)
  - ViewModel e LiveData
  - Coroutines (`Dispatchers.IO` e `Dispatchers.Main`)

## Arquitetura MVVM

O projeto segue o padrão de arquitetura MVVM:

- **Model:** Representa os dados e as regras de negócio. No projeto, o modelo é a classe `Calculator`, que armazena as operações realizadas.
- **View:** A camada de interface do usuário, gerenciada pelas Activities e Fragments.
- **ViewModel:** A lógica de interface é separada da View e colocada no `CalculatorViewModel`, que faz a ponte entre os dados do Model e a interface do usuário.

## Como Executar o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/calculadora-pekus.git
    ```
2. Abra o projeto no Android Studio.
3. Certifique-se de que todas as dependências estão corretamente instaladas.
4. Execute o projeto em um dispositivo ou emulador Android.

## Estrutura de Diretórios

```bash
CalculadoraPekus/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/seuusuario/calculadorapekus/
│   │   │   │   ├── adapter/             # Adaptadores para RecyclerViews
│   │   │   │   ├── dao/                 # Interfaces para operações no banco de dados
│   │   │   │   ├── database/            # Configuração e inicialização do Room Database
│   │   │   │   ├── listener/            # Interfaces para eventos e ações
│   │   │   │   ├── model/               # Modelos de dados (Calculator)
│   │   │   │   ├── repository/          # Repositórios para manipulação de dados
│   │   │   │   ├── view/                # Activities e Fragments (Views)
│   │   │   │   ├── viewHolder/          # ViewHolders para RecyclerViews
│   │   │   │   ├── viewModel/           # ViewModels (CalculatorViewModel)
│   │   ├── res/                         # Arquivos de layout e recursos
│   ├── AndroidManifest.xml
├── build.gradle
