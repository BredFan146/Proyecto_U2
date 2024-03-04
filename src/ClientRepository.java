import java.util.ArrayList;

public class ClientRepository {
    private ArrayList<Client>clients;
    public ClientRepository(){
        this.clients=new ArrayList<>();
    }
    public void addClient(Client client){
        clients.add(client);
    }
    public void removeClient(Client client){
        clients.remove(client);
    }
    public void updateClient(Client oldClient,Client newClient){
        int index=clients.indexOf(oldClient);
        if (index!=-1){
            clients.set(index, newClient);
        }
    }
    public ArrayList<Client>getAllClients(){
        return clients;
    }
}
