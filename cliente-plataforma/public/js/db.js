/**
 * Created by Joaco on 23/09/2015.
 */
 function addinsql(data)
          {
          $.get("/update?data="+data,function(data){
             if(data.yes==1)
            {
          console.log("data updated");
          }
           else
           {
           console.log("error");
            }
           });
          }