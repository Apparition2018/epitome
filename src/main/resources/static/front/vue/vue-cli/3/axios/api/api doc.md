```
http://localhost:9000/api

url                 method      param           desc
/contactList        get
/contact/new/form   post        name, tel       content-type:form-data
/contact/new/json   post        name, tel       content-type:application/json
/contact/edit       put         name, tel, id   content-type:application/json
/contact            delete      id
```