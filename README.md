# FinanceFlow

FinanceFlow é um aplicativo de gerenciamento financeiro pessoal desenvolvido com **Jetpack Compose**, **Kotlin** e **Room**. Ele permite registrar lançamentos de receitas e despesas, armazenando os dados localmente em um banco de dados SQLite.
Este projeto faz parte da atividade final da disciplina de Android Aplicado na Pós Graduação de Programação para Dispositivos Móveis. Onde cada aluno tinha como objetivo desenvolver um aplicativo android para gerenciamento das suas finanças.

---

##  Tecnologias

- **Kotlin 1.9**
- **Jetpack Compose** para UI moderna e responsiva
- **Room 2.8.0-beta01** para persistência de dados
- **SQLite KTX** para interações com banco local
- **Coroutines** para operações assíncronas
- **Gradle** como ferramenta de build

---

##  Funcionalidades

- Cadastro de lançamentos financeiros (receitas e despesas)
- Listagem de lançamentos ordenados por data
- Armazenamento seguro e local usando Room e SQLite
- Interface moderna e responsiva com Jetpack Compose

---

##  Estrutura do Projeto


Resultado alcançado

<img width="493" height="817" alt="image" src="https://github.com/user-attachments/assets/f4014641-48c7-4336-9e76-1cb130500c78" />



 git clone https://github.com/laurindodumba/FinanceFlow/tree/main

```bas

app/
├─ src/main/java/com/example/financeflowv1/
│ ├─ data/
│ │ ├─ LancamentoDao.kt 
│ │ └─ LancamentoEntity.kt 
│ ├─ database/
│ │ └─ AppDatabase.kt 
│ ├─ view/
│ │ └─ ... 
│ └─ MainActivity.kt
└─ build.gradle






