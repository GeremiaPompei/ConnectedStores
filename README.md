# ConnectedStores

## Indice

1. <a href="#1-introduzione">Introduzione</a>
2. <a href="#2-client">Client</a>    
   2.1 <a href="#21-configurazione">Configurazione</a>    
   2.2 <a href="#22-operazioni-principali">Operazioni Principali</a>    
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.1 <a href="#221-push">Push</a>     
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.2 <a href="#222-remove">Remove</a>     
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.3 <a href="#223-get">Get</a>     
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.4 <a href="#224-get-all">Get-all</a>    
   2.3 <a href="#23-operazioni-secondarie">Operazioni-Secondarie</a>
3. <a href="#3-server">Server</a>
4. <a href="#4-ssl">SSL</a>

## 1. Introduzione

**ConnectedStores** è un'applicazione sviluppata per testare le funzionalità del framework Jersey. Proprio per
questo    
motivo essa comprende due parti fondamentali in cui viene divisa:

- Client
- Server

Entrambi i lati coesistono insieme parallelamente.

In tale applicazione "giocattolo" vi è un local store che può contenere delle risorse. Queste ultime possono essere    
create, visualizzate o rimosse. Avendo più macchine con tale applicazione avviata si possono caricare o modificare
le    
risorse negli store altrui.

<a href="#indice">^ back ^</a>

## 2. Client

### 2.1 Configurazione

Per eseguire **ConnectedStores** bisogna aver scaricato precedentemente **java 11** e **Maven**. Per eseguire    
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

Appena il programma viene avviato viene chiesto

- l'**indirizzo del sender**, ovvero l'indirizzo dal quale inviare richieste rest e sul quale riceverle (
  es. https://localhost:8080/).

Premuto *Enter* possiamo eseguire varie operazioni sugli stores disponibili (il nostro compreso).

### 2.2 Operazioni Principali

#### 2.2.1 Push

``` 
push indirizzoDestinatario idRisorsa nomeRisorsa descrizioneRisorsa 
```   

Comando per creata una risorsa ed inserirla all'interno dello store con l'indirizzo indicato. La risposta a tale    
messaggio sarà

``` 
[CLIENT] : push -> true 
```   

se l'operazione è avvenuta con successo o

``` 
[CLIENT] : push -> false 
```   

altrimenti. Se viene inserita una risorsa con lo stesso id di un'altra gia presente in quello store l'operazione di  
push    
fallisce.

#### 2.2.2 Remove

``` 
remove indirizzoDestinatario idRisorsa 
```   

Comando per rimuovere la risorsa con l'id inserito. La risposta può essere

``` 
[CLIENT] : remove -> true 
```   

se l'operazione è avvenuta con successo o

``` 
[CLIENT] : remove -> false 
```   

altrimenti, ovvero nel caso l'id non appartiene a nessuna risorsa nello store con l'indirizzo indicato.

#### 2.2.3 Get

```
 get indirizzoDestinatario idRisorsa 
 ```   

Comando per ottenere una risorsa con n certo id. La risposta può essere

``` 
[CLIENT] : get -> {oggetto} 
```   

se l'oggetto con l'id indicato esiste nello store con tale indirizzo, altrimenti

``` 
[CLIENT] : get -> null 
```

#### 2.2.4 Get-all

``` 
get-all indirizzoDestinatario 
```   

Comando per ottenere tutti i dati dello store con tale indirizzo. La risposta sarà

``` 
[CLIENT] : get-all -> [dati] 
```

### 2.3 Operazioni Secondarie

- **local-store**: mostra tutte le risorse presenti nello store locale
- **help**: mostra la lista delle operazioni e la dicitura di un comando
- **exit**: termina l'esecuzione del programma

<a href="#indice">^ back ^</a>

## 3. Server

Durante l'esecuzione possono arrivare messaggi che informano le operazioni avvenute a livello server. Queste
avvisano    
l'utente dell'avvenuta operazione sul proprio store. Tali messaggi sono

``` 
Request from indirizzoMittente 
[SERVER]: operazione -> targetOperazione 
```   

<a href="#indice">^ back ^</a>

# 4. SSL

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