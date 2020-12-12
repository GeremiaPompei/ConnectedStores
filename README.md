# ConnectedStores
##Indice

1. <a href="#introduzione">Introduzione</a>
2. <a href="#client">Client</a>
3. <a href="#server">Server</a>

## Introduzione

**ConnectedStores** è un'applicazione sviluppata per testare le funzionalità del framework Jersey. Proprio per questo
motivo essa comprende due parti fondamentali in cui viene divisa:

- Client
- Server

Entrambi i lati coesistono insieme parallelamente.

In tale applicazione "giocattolo" vi è un local store che può contenere delle risorse. Queste ultime possono essere
create, visualizzate o rimosse. Avendo più macchine con tale applicazione avviata si possono caricare o modificare le
risorse negli store altrui.

<a href="#indice">^ back ^</a>

## Client

### Configurazione

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

- l'**indirizzo ip del sender**, ovvero l'indirizzo dal quale inviare richieste rest e sul quale riceverle.

Premuto *Enter* possiamo eseguire varie operazioni sugli stores disponibili (il nostro compreso).

### Operazioni principali

#### Push

```
push indirizzoIp idRisorsa nomeRisorsa descrizioneRisorsa
```

Comando per creata una risorsa ed inserirla all'interno dello store con l'indirizzo ip indicato. La risposta a tale
messaggio sarà

```
[CLIENT] : push -> true
```

se l'operazione è avvenuta con successo o

```
[CLIENT] : push -> false
```

altrimenti. Se viene inserita una risorsa con lo stesso id di un'altra gia presente in quello store l'operazione di push
fallisce.

#### Remove

```
remove indirizzoIp idRisorsa
```

Comando per rimuovere la risorsa con l'id inserito. La risposta può essere

```
[CLIENT] : remove -> true
```

se l'operazione è avvenuta con successo o

```
[CLIENT] : remove -> false
```

altrimenti, ovvero nel caso l'id non appartiene a nessuna risorsa nello store con l'indirizzo ip indicato.

#### Get

```
get indirizzoIp idRisorsa
```

Comando per ottenere una risorsa con n certo id. La risposta può essere

```
[CLIENT] : get -> {oggetto}
```

se l'oggetto con l'id indicato esiste nello store con tale indirizzo, altrimenti

```
[CLIENT] : get -> null
```

#### Get-all

```
get-all indirizzoIp
```

Comando per ottenere tutti i dati dello store con tale indirizzo ip. La risposta sarà

```
[CLIENT] : get-all -> [dati]
```

### Operazioni secondarie

- **local-store**: mostra tutte le risorse presenti nello store locale
- **help**: mostra la lista delle operazioni e la dicitura di un comando
- **exit**: termina l'esecuzione del programma

<a href="#indice">^ back ^</a>

## Server

Durante l'esecuzione possono arrivare messaggi che informano le operazioni avvenute a livello server. Queste avvisano
l'utente dell'avvenuta operazione sul proprio store. Tali messaggi sono

```
Request from http://indirizzoIp:8080/
[SERVER]: operazione -> targetOperazione
```

<a href="#indice">^ back ^</a>