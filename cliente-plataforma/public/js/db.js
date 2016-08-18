/**
 * Created by Joaco on 23/09/2015.
 */
 function addinsql(data)
          {
          $.get("/insert?data="+data,function(data){
             if(data.yes==1)
            {
          console.log("data inserted");
          }
           else
           {
           console.log("error");
            }
           });
          }
 function updateinsql(data)
          {
          $.get("/update?data="+data,function(data){
             if(data.yes==1)
            {
          console.log("data update");
          }
           else
           {
           console.log("error");
            }
           });
          }

       function executeInsert()
          {
          $.get("/select",function(){
            
           });
          }

