package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Salva, cancella e interroga la tabella City su un multitenant a database separati, schemi separati e utenti separati.
 * Risponde a:
 * 
 *  Inserimento della città "Mumbai" nel tenant "test1"
 *  $  curl -X POST http://10.10.252.106:8080/ -H 'Content-Type: application/json' -H 'X-TenantID: test1' -d '{"name":"Mumbai"}'
 *  
 *  Inserimento della città "Bari" nel tenant "test2"
 *  $  curl -X POST http://10.10.252.106:8080/ -H 'Content-Type: application/json' -H 'X-TenantID: test1' -d '{"name":"Mumbai"}'
 * 
 *  Restituisce tutti i record del tenant "test1"
 *  
 *  $ curl -X GET   http://10.10.252.106:8080/ -H 'Content-Type: application/json' -H 'X-TenantID: test1'
 *  
 *  Restituisce tutti i record del tenant "test2"
 *  
 *  $ curl -X GET   http://10.10.252.106:8080/ -H 'Content-Type: application/json' -H 'X-TenantID: test2'
 * 
 * 
 * Richiede tre db con tre utenti separati e tre schemi separati.
 * - vedi application.yml per il db di configurazione dei tenant
 * - vedi DDL.sql
 * 
 * @author chirico.francesco
 * 
 * 
 *
 */
@SpringBootApplication
@ComponentScan("com.example.*")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
