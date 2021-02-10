/*
 * Rec.java
 *
 * Created on 7 giugno 2004, 11.33
 */

package it.mynext.iaf.nettrs;

/**
 *
 * @author  BERGANTINO Nicola - www.mynext.it
 */
public class Rec {
    /**Definizione delle size di default per i tipi di campo */
    public final int DEF_FLD_SZ         =151;

    public final int DEF_DOUBLE_SZ   =41;
    public final int DEF_FLOAT_SZ   =31;
    public final int DEF_INT_SZ =31;
    public final int DEF_SHORT_SZ =21;
    public final int DEF_BYTE_SZ =11;
   
    /**Definizione dei tipi di dato per i campi */
    public static final int FDT_STR =0;
    public static final int FDT_DOUBLE   =1;
    public static final int FDT_FLOAT   =2;
    public static final int FDT_INT =3;
    public static final int FDT_SHORT =4;
    public static final int FDT_BYTE =5;
   
    public static class FLD{
        public FLD(){
            NAME=null;
            TYPE=0;
            SIZE=0;
        }
        public String NAME;      //NOME DEL CAMPO
        public int TYPE;         //TIPO
        public int SIZE;         //DIMENSIONE
    };

    public static class FLDVAL{
        public FLDVAL(){
            VALUE=null;
        }
        public String VALUE;      //VALORE  
    };

    public static class REC{
        public REC(){
            FLDREC=null;
        }
       
        public FLDVAL FLDREC[];        //ARRAY DI FIELD PER RECORD  
    };

    /** Membri protetti */
    protected int currec; //numero di record corrente
   
    protected int fldcount;  //numero di campi per record
    protected int reccount;  //numero di record

    protected FLD fld[];   //array dei nomi ed informazioni sui campi
    protected REC rec[];   //array di record

    protected String name;
   
    /** Creates a new instance of Rec */
    public Rec() {
         currec=-1;

         fldcount=0;
         reccount=0;

         fld=null;
         rec=null;

         name = null;
    }
    /** Si sposta all'ultimo record restituisce false se non ci sono record */
    public boolean last(){
        if(reccount>0){
            currec=reccount-1;
            return(true);
        }

        return(false);
    }
    /** Si posiziona al primo rec restituisce false se non ci sono record */
    public boolean first(){
        if(reccount>0){
              currec=0;
              return(true);
        }

        return(false);
    }
    /** Passa al record successivo, restituisce false se si � all'inizio del rec */
    public boolean prev(){
        if(currec>-1)
            return(--currec>-1);

        return(false);
    }
    /** Passa al record successivo, restituisce false se si � alla fine del rec */
    public boolean next(){
        if(currec<reccount)
            return(++currec<reccount);

        return(false);
    }  
   
    /** Restituisce direttamente il puntatore a char in cui si trova il valore */
    public String getValue(String name){
        try{
            if(currec>-1 && currec<reccount){
                  int id = getIdFromName(name);

                  if(id>-1){  
                      if(rec[currec].FLDREC[id]!=null)
                        return(rec[currec].FLDREC[id].VALUE);          
                  }
            }

            return("");    
        }
        catch(Exception e){
            System.out.println("Rec::getValue ERR "+e.getMessage());
            return("");
        }
    }
    /** Restituisce il puntatore char in cui si trova il valore dall'id del campo */
    public String getValue(int id){
        try{
            if(currec>-1 && currec<reccount){
                  if(id>-1 && id<fldcount){                  
                      if(rec[currec].FLDREC[id]!=null)
                            return(rec[currec].FLDREC[id].VALUE);
                  }
            }

            return("");
        }
        catch(Exception e){
            System.out.println("Rec::setValue ERR "+e.getMessage());
            return("");
        }
    }

    /** Restituisce il numero di record contenuti nell'interfaccia */
    public int getCount(){
        return(reccount);
    }    
    /** Restituisce il tipo del campo pasasto per nome */
    public int getFldType(String name){
        int id = getIdFromName(name);
        if(id>-1)
            return(fld[id].TYPE);

        return(0);
    }
    /** Restituisce la dimensione del campo passato per nome */
    public int getFldSize(String fldname){
        int id = getIdFromName(fldname);
        if(id>-1)
            return(fld[id].SIZE);

        return(0);
    }
    /** Restituisce il tipo del campo in base all'id passato */
    public int getFldType(int id){
        if(id>-1 && id<fldcount)
            return(fld[id].TYPE);

        return(-1);
    }
    /** Restituisce il tipo del campo in base all'id passato */
    public int getFldSize(int id){
        if(id>-1 && id<fldcount)
            return(fld[id].SIZE);

        return(0);
    }
    /** Restituisce il nome del campo dall'id */
    public String getFldName(int id){
        if(id>-1 && id<fldcount)
            return(fld[id].NAME);

        return(null);
    }

    /** Restituisce il numero di campi nel record */
    public int getFldCount(){
        return(fldcount);
    }
    /** Inizializza il record */
    public boolean init(int fldcount, int reccount){
        try{
            if(reccount>0 && fldcount>0)    
            {
                 currec=0;

                 this.fldcount=fldcount;
                 this.reccount=reccount;

                 //ALLOCA L'ARRAY DI STRUTTURE PER I FIELD
                 fld = new FLD[fldcount];
                 //ALLOCA TUTTI GLI ELEMENTI DELL'ARRAY
                 for(int i=0;i<fldcount;i++){
                     fld[i]=new FLD();
                 }
                 
                 //ALLOCA L'ARRAY DI STRUTTURE PER I RECORD
                 rec = new REC[reccount];

                 //ALLOCA L'ARRAY DI STRUTTURE PER I VALORI DEI CAMPI
                 for(int i=0;i<reccount;i++){
                      if(rec[i]==null)
                          rec[i]=new REC();

                      rec[i].FLDREC = new FLDVAL[fldcount];
                 }              

                 return(true);
            }

            return(false);
        }
        catch(Exception e){
            System.out.println("Rec::init ERR "+e.getMessage());
            return(false);
        }
    }
    /** Imposta le informazioni su un campo specificato da id */
    public boolean setFldData(int id, String name, int type, int size){
        if(id>-1 && id<fldcount){
            fld[id].SIZE = type==FDT_STR?size>0?size:DEF_FLD_SZ:getsizenume(type);
            fld[id].TYPE = type;
            fld[id].NAME = name;

            return(true);
        }

        return(false);    
    }
    /** Setta il valore per il campo indicato da name per il record corrente */
    public boolean setValue(String name, String value){
        try{
            if(currec>-1 && currec<reccount && value!=null){
                  int id = getIdFromName(name);

                  if(id>-1){
                      if(rec[currec].FLDREC[id]==null)
                          rec[currec].FLDREC[id]=new FLDVAL();

                      rec[currec].FLDREC[id].VALUE = value;

                      return(true);
                  }
            }

            return(false);    
        }
        catch(Exception e){
            System.out.println("Rec::setValue ERR "+e.getMessage());
            return(false);
        }
    }
    /** Setta il valore del campo indicato da idfld del record indicato da idrec. */
    public boolean setValue(int idfld, int idrec, String value){
        try{
            if(idrec>-1 && idrec<reccount && idfld>-1 && idfld<fldcount && value!=null){
                if(rec[idrec].FLDREC[idfld]==null)
                    rec[idrec].FLDREC[idfld] = new FLDVAL();

                rec[idrec].FLDREC[idfld].VALUE=value;

                return(true);
            }

            return(false);    
        }
        catch(Exception e){
            System.out.println("Rec::setValue ERR "+e.getMessage());
            return(false);
        }
    }

    /** Restituisce la struttura field da un id */
    public FLD getFldData(int id){
        if(id>-1 && id<fldcount)
            return(fld[id]);

        return(null);
    }
    /** Restituisce il record corrispondente all'ID passato per parametro. Utilizzato dalla CRecTrs */
    public REC getRecData(int id){
        if(id>-1 && id<reccount)
            return(rec[id]);

        return(null);
    }
    /** Restituisce il valore di un campo dall'id del campo e dall'id del record */
    public String getValue(int idfld, int idrec){
        if(idrec>-1 && idrec<reccount){
            if(idfld>-1 && idfld<fldcount){
                if(rec[idrec].FLDREC[idfld]!=null)
                    return(rec[idrec].FLDREC[idfld].VALUE);
            }
        }

        return("");
    }
    /** Restituisce il nome assegnato al record */
    public String getName(){return(name);}
    /** Assegna un nome al record */
    public void setName(String name){this.name=name;}

    /** Setta il valore di un campo da un numero double */
    public boolean setValue(String fldname, double value){
        //Double d = new Double(value);
        return(setValue(fldname, ""+value/*d.toString()*/));    
    }
    /** Setta il valore di un campo da un numero intero */
    public boolean setValue(String fldname, int value){
        //Integer i = new Integer(value);
        return(setValue(fldname, ""+value/*i.toString()*/));
    }
    /** Copia la struttura del record corrente su un nuovo record restituito dalla funzione
        copyrecord = true: copia anche i record contenuti altrimenti alloca un record vuoto
     */
    public Rec copyStruct(boolean copyrecord){
        try{
            Rec r = new Rec();
            //inizializza il nuovo record
            r.init(fldcount, copyrecord?reccount:1);
            //copia i field locali su quelli del nuovo record
            copyFld(r);
            //System.arraycopy((Object)this.rec, 0, (Object)r.rec, this.reccount);
            //copia il nome del record corrente
            r.setName(this.name);
            //copia i record se necessario
            if(copyrecord){
                for(int i=0;i<reccount;i++){
                   for(int j=0;j<fldcount;j++){
                        if(rec[i].FLDREC[j].VALUE!=null)
                             r.setValue(j, i, rec[i].FLDREC[j].VALUE);
                   }
                }
            }

            return(r);
        }
        catch(Exception e){
            System.out.println("Rec::copyStruct ERR "+e.getMessage());
            return(null);
        }
    }  
    /** Copia la struttura del record corrente, per default non copia i record presenti ma solo la struttura
        copyrecord = false
     */
    public Rec copyStruct(){
        return(copyStruct(false));
    }    
    /** Restituisce l'id del campo dal nome. Esegue una ricerca sequenziale */
    protected int getIdFromName(String name){
         for(int i=0;i<fldcount;i++)
         {
             if(fld[i].NAME.equals(name))
                   return(i);
         }

         return(-1);
    }    
    /** Resituisce la dimensione max per i valori numerici a seconda del tipo */
    protected int getsizenume(int type){
         switch(type){
              case FDT_DOUBLE:
                   return(DEF_DOUBLE_SZ);
              case FDT_FLOAT:
                   return(DEF_FLOAT_SZ);
              case FDT_INT:
                   return(DEF_INT_SZ);
              case FDT_SHORT:
                   return(DEF_SHORT_SZ);
              case FDT_BYTE:
                   return(DEF_BYTE_SZ);
              default:
                   return(DEF_DOUBLE_SZ);
         }
    }
    /**copia i dati dell'array fld dal record locale al record destinazione*/
    protected boolean copyFld(Rec rdest){
        try{
            for(int i=0;i<fldcount;i++){
                rdest.fld[i].NAME =this.fld[i].NAME;
                rdest.fld[i].SIZE = this.fld[i].SIZE;
                rdest.fld[i].TYPE = this.fld[i].TYPE;
            }
            return(true);
        }
        catch(Exception e){
            System.out.println("Rec::copyFld ERR "+e.getMessage());
            return(false);
        }
    }
}
