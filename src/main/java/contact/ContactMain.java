package contact;


class ContactMain {
    public static void main(String[] args) throws Exception{
        String mode = null;
        String action = null;
        String phone = null;
        String email = null;
        String name = null;

        for (int i = 0; i < args.length; ++i) {
            if ("-mode".equals(args[i])) {
                mode = args[++i];
            } else if ("-action".equals(args[i])) {
                action = args[++i];
            } else if ("-name".equals(args[i])) {
                name = args[++i];
            } else if ("-email".equals(args[i])) {
                email = args[++i];
            } else if ("-phone".equals(args[i])) {
                phone = args[++i];
            }
        }

        if( action == null || name == null || mode == null){
            exit();
        }
        Contact contact;
        if(mode.equals("storage")){
            contact = new ContractStorageService();
        }else{
            contact = new ContactTransactionService();
        }
        switch (action){
            case "get" : if(name == null || email == null) {
                            exit();
                          }
                        contact.getPhone(name, email);
                        break;
            case "scan" :  contact.getList(name);break;
            case "add" : if(email == null || phone == null){
                                exit();
                        }
                        contact.add(name,email, phone);
                        break;
            default: exit();
        }

        System.exit(0);
    }

    private static void exit(){
        System.out.println("Program exited");
        System.exit(0);
    }
}