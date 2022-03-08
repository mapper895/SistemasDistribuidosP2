# Sistemas Distribuidos P2 - Cliente, Servidor y una Interfaz Remota

## Funcionamiento
- Suponga que tiene una tienda en línea al estilo de Amazon ( quien fungirá como el cliente en este ejercicio), dicha tienda acepta pagos con tarjeta de crédito.
- Se tiene un banco ( servidor) que ofrece los servicios ( métodos) para hacer válidos los pagos con tarjeta de crédito; dicho método es invocado por un cliente de forma remota.
- **Escriba el código del servidor**, el cual se ejecute en una IP y un puerto fijo, e imprima en pantalla que está en ejecución y en espera de conexiones.
- Cuando reciba una conexión imprima el mensaje de que está atendiendo un cliente y otro mensaje cuando ha terminado de atenderlo.
- **Escriba el código del cliente**
- Defina la interfaz remota `int pago(cantidad_a_pagar, tarjeta, cvv)`
- Construya el STUB requerido para que el cliente haga llamadas con el método:
`int pago ( cantidad_a_pagar, tarjeta, cvv )`
en caso de recibir un valor de retorno de 0, imprima en pantalla que la transacción no fue exitosa y que intente con otro medio de pago. Y si recibe un valor de retorno de 1, imprima en pantalla que la transacción fue exitosa, se confirma su compra y su pedido está en camino.
- Construya el SKELETON requerido para que el servidor acepte las llamadas con el método: `int pago(cantidad_a_pagar, tarjeta, cvv)`
- Revise si existe la tarjeta y el CVV de la tarjeta, en caso de que existan, entonces verifica que existe saldo suficiente asociado a la tarjeta, y en caso de que si , regrese un valor de **1**, indicando que la transacción fue exitosa, o un **0** que indica que la transacción no fue exitosa

### Ejecución 
Para compilar el servidor se utiliza el comando
```sh
javac server/Server.java
javac server/ObjectSkeleton.java
```
Para correr el servidor se ocupa el comando
```sh
java server/Server
```
Una vez ejecutado el lado del servidor, este entrara espera de algun cliente en el puerto 1007-

Para el lado del cliente, para compilarlo se ocupa el comando
```sh
javac client/Client.java
javac client/ObjectStub.java
```
Para ejecutar el cliente, se ocupa el comando
```sh
java client/Client <nombre o IP del Equipo> <numero de puerto>
# Por ejemplo
java client/Client 127.0.0.1 1007
```
