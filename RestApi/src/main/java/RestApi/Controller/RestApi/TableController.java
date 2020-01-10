package RestApi.Controller.RestApi;

import Data.Context.MemoryContext.TableContextMemory;
import Data.Repository.TableRepository;
import Factory.Factory;
import logic.Interfaces.ITableLogic;
import logic.TableLogic;
import RestApi.VOModels.VOTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TableController {
    private ITableLogic tableLogic = Factory.TableLogic();


    @PostMapping("/table")
    public ResponseEntity create(@RequestBody VOTable table) {
        if(tableLogic.getBy(table.getName()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Table Already exists");
        }
        if(tableLogic.add(table)){
            return ResponseEntity.status(HttpStatus.CREATED).body(table);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @PutMapping("/table/{id}")
    public ResponseEntity update(@RequestBody VOTable table){
        System.out.println(table.getDisabled());
        if(tableLogic.edit(table)){
            return ResponseEntity.status(HttpStatus.CREATED).body(table);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @DeleteMapping("/table/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(tableLogic.getBy(id).getName() != null) {
            tableLogic.remove(tableLogic.getBy(id));
            return ResponseEntity.status(HttpStatus.OK).body("Table deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Table not found");
    }

    @GetMapping("/table")
    public ResponseEntity read(){
        if(tableLogic.getAll() != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    tableLogic.getAll()
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No table found");
    }

    @GetMapping("/table/{id}")
    public ResponseEntity read(@PathVariable int id){
        if(tableLogic.getBy(id) != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    tableLogic.getBy(id)
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Table not found");
    }
}
