# RESTService

## Indice

1. <a href="#1-introduzione">Introduzione</a>
2. <a href="#2-client">Client</a>    
   2.1 <a href="#21-configurazione">Configurazione</a>    
   2.2 <a href="#22-post">POST</a>    
   2.3 <a href="#23-get">GET</a>
3. <a href="#3-server">Server</a>
4. <a href="#4-ssl">SSL</a>

## 1. Introduzione

**RESTService** è un'applicazione sviluppata per testare le funzionalità del framework Jersey. Proprio per
questo    
motivo essa comprende due parti fondamentali in cui viene divisa:

- Client
- Server

Entrambi i lati coesistono insieme parallelamente.

In tale applicazione "giocattolo" viene creata o ottenuta una risorsa **Rec** tramite chiamate *REST* di tipo *POST* e *GET*.

<a href="#indice">^ back ^</a>

## 2. Client

### 2.1 Configurazione

Per eseguire **RESTService** bisogna aver scaricato precedentemente **java 11** e **Maven**. Per eseguire    
l'applicazione dopo averla scaricate basta aprire il terminale ed accedere all'interno di questa cartella con il comando

``` 
cd pathdirectory/nomedirectory 
```   

Acceduti alla directory per eseguire la build bisogna dare il comando

``` 
mvn package 
```   

Arrivati a questo punto finalmente possiamo avviare l'app con il comando

``` 
mvn exec:java 
```   

Appena il programma viene avviato apparirà una schermata di configurazione iniziale dove l'utente potrà selezionare l'indirizzo IP dove far partire le istanze del client e del server.

### 2.2 POST

Tramite la sezione apposita può essere costruito un oggetto Rec e inviato tramite una chiamata *REST* di tipo *POST*.

Per creare una risorsa **Rec** basta aggiungere i campi *Field* e *Rec* tramite gli appositi pulsanti e settarli in tal modo:
- Per settare un **FIELD** basta premere nella relativa colonna sulla tabella in alto a sinistra e immettere i campi *name*, *type* e *size*. Dopodichè basta premere il pulsante *Set* *field*.

- Per settare un **Rec** basta premere nella relativa casella sulla tabella in alto a sinistra e immettere il campo *value*. Dopodichè basta premere il pulsante *Set* *rec*.

Per finire la procedura di invio basta immettere il nome del *Rec*, l'indirizzo ip del destinatario e premere il pulsante *POST*.

### 2.3 GET

Tramite la senzione apposita è possibile visualizzare una risorsa *Rec* dato l'indirizzo ip dell'host di chi la possiede.

Per fare ciò basta immetter l'indirizzo ip e ottenere la risorsa premendo il pulsante *GET*. La risorsa apparirà nell'are di testo sottostante al pulsante.

<a href="#indice">^ back ^</a>

## 3. Server

Durante l'esecuzione possono arrivare messaggi che informano le operazioni avvenute a livello server. Queste
avvisano l'utente dell'avvenuta operazione sul proprio server. Tali messaggi verranno visualizzati nell'area di testo sottostante.

<a href="#indice">^ back ^</a>

## 4. SSL

Per rendere la comunicazione tra client e server sicura è stato implementato il protocollo **https**, ovvero il
protocollo
*http* a cui viene aggiunta una crittografia asincrona grazie ad **SSL**. Per fare ciò c'è bisogno di generare le chiavi
private e pubbliche del client e server. Possiamo fare ciò grazie al programma *keytool*. Il primo comando da eseguire
per generare la chiave privata è

``` 
keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias mykey -keystore myKeyStore.jks
``` 

Tale comando chiedrà alcune informazioni per configurarela chiave oltre ad una password per proteggerla.

Fatto ciò bisogna esportare da tale chiave il certificato in un file con tale comando

``` 
keytool -export -alias mykey -keystore myKeyStore.jks -file mykey.cert
``` 

Questo chiede di inserire la password della chiave privata.

Fatto ciò l'ultimo passaggio è importare il certificato esportato dalla chiave privata in una pubblica con tale comando

``` 
keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts
``` 

Questo comando chiede di inserire una password per proteggere tale chiave pubblica.

Tale programma utilizza le coppie di chiavi presenti nella directory *ssl* presente a sua volta nella directory
principale.

<a href="#indice">^ back ^</a>
