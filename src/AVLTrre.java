public class AVLTrre {
    
    private Node root;


    public AVLTrre(){
        this.root = null;
    }

    public int height(Node node){
           if (node == null) {
               return 0;
           }
           return node.getHeight();
    }

    public int getBalance(Node node){
        if (node == null) {
            return 0;
        }

        return height(node.getLeft()) - height(node.getRight());
    }

    public void insert(int value){
        System.out.println("Valor a insertar " + value);
        root = insertRec(root, value);
    }

    public Node insertRec(Node node, int value){

       if(node == null){
         Node newNode = new Node(value);
         newNode.setHeight(1);
         System.out.println("Nodo insertado: " + newNode.getValue() + " balance al insertar = " + getBalance(newNode));
         return newNode;
       }

       if (value < node.getValue()) {
           node.setLeft(insertRec(node.getLeft(), value));
       }else if (value > node.getValue()) {
           node.setRight(insertRec(node.getRight(), value));
       }else{
          return node;
       }

       System.out.println("Node actual" + node.getValue());

       // actualizar la altura de este ancestro node

       int altura = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
       node.setHeight(altura);

       System.out.println("\tAltura = " + node.getHeight());

       int balance = getBalance(node);
       System.out.println("\tBalance = " + balance);

       //caso Izquierda - Izquierda
       if (balance > 1 && value < node.getLeft().getValue()) {
           System.out.println("Rotacion Derecha");
           return rightRotate(node);
       }

       //caso derecha derecha
       if (balance < -1 && value > node.getRight().getValue()) {
           System.out.println("Rotacion Izquierda");
           return leftRotate(node);
       }

       //Caso Izquierda - Derecha
        if ((balance > 1 && value > node.getLeft().getValue()) ) {
            System.out.println("Cambio");
            System.out.println("Rotacion Izquierda derecha");
            return rightRotate(node);
       }

       // Caso Derecha - Izquierda
     if (balance < -1 && value < node.getRight().getValue()) {
         System.out.println("Cambio");
        System.out.println("Rotación Derecha-Izquierda");
        return leftRotate(node);
    }   


       return node;
    }

    private Node rightRotate(Node padre) {
    Node hijoIzq = padre.getLeft();
    Node temp = hijoIzq.getRight();


    hijoIzq.setRight(padre);
    padre.setLeft(temp);


    padre.setHeight(Math.max(height(padre.getLeft()), height(padre.getRight())) + 1);
    hijoIzq.setHeight(Math.max(height(hijoIzq.getLeft()), height(hijoIzq.getRight())) + 1);


    return hijoIzq;
}

private Node leftRotate(Node padre) {
    Node hijoDer = padre.getRight();
    Node temp = hijoDer.getLeft();

    hijoDer.setLeft(padre);
    padre.setRight(temp);

  
    padre.setHeight(Math.max(height(padre.getLeft()), height(padre.getRight())) + 1);
    hijoDer.setHeight(Math.max(height(hijoDer.getLeft()), height(hijoDer.getRight())) + 1);

    return hijoDer;
}


}
