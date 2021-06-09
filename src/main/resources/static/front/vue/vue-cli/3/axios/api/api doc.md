```
http://localhost:9000/api

url                 method      param           desc
/contactList        get
/contacat/new/form  post        name, tel       content-type:form-data
/contacat/new/json  post        name, tel       content-type:application/json
/contacat/edit      put         name, tel, id   content-type:application/json
/contacat           delete      id
```