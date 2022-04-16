
# Müşteri Arşivleme Sistemi

Müşteri Dosya Arşivleme Sistemi
## Bilgisayarınızda Çalıştırın

Projeyi klonlayın

```bash
  git clone https://github.com/erdoganakdeniz/archiving-system.git
```

Proje dizinine gidin

```bash
  cd my-project
```



  ## Dağıtım

Bu projeyi Docker ortamında çalıştırmak için ;

Build etmek için
```bash
  docker build --tag archiving-system .
```

Çalıştırmak için
```bash
  docker run -p8080:8080 archiving-system
```
  
  
## API Kullanımı



## Auth Endpoint
#### Kayıt İşlemi İçin

```http
  POST /auth/signup
```

````
{
    "userName":"deneme",
    "userPassword":"123456"
}
````
#### Login İşlemi İçin

```http
  POST /auth/login
```

````
{
    "userName":"deneme",
    "userPassword":"123456"
}
````

## Customer Endpoint
#### Tüm Müşterileri Getirmek İçin

```http
  GET /customer
```
#### Dönüş Değeri

````
{
        "id": 1,
        "firstName": "Namık",
        "lastName": "Sarman",
        "identificationNumber": "223345655",
        "companyName": "Sasko",
        "phoneNumber": "55564642125",
        "email": "namiksarman@gmail.com",
        "address": "sadasdasdasd"
}
````
#### Müşteri Kayıt İşlemi İçin

```http
  POST /customer
```

````
{
        "id": 1,
        "firstName": "Namık",
        "lastName": "Sarman",
        "identificationNumber": "223345655",
        "companyName": "Sasko",
        "phoneNumber": "55564642125",
        "email": "namiksarman@gmail.com",
        "address": "sadasdasdasd"
}
````

#### İstenen Müşteri Kaydını Getirmek

```http
  GET /customer/{customerId}
```

````
{
        "id": 2,
        "firstName": "Hamdi",
        "lastName": "Zıpkın",
        "identificationNumber": "84845622",
        "companyName": "Nosad",
        "phoneNumber": "555646887425",
        "email": "hamdizipkin@gmail.com",
        "address": "Antalya"
}
````
## File Endpoint
#### Tüm Dosyaları Getirmek İçin

```http
  GET /file
```
#### Dönüş Değeri

````
{
        "id": 7,
        "fileName": "NamıkınBelgesi",
        "fileDescription": "Namık ve ailesinin hayatı",
        "customer": {
            "id": 1,
            "firstName": "Namık",
            "lastName": "Sarman",
            "identificationNumber": "223345655",
            "companyName": "Sasko",
            "phoneNumber": "55564642125",
            "email": "namiksarman@gmail.com",
            "address": "sadasdasdasd"
        }
}
````
#### Dosya Kayıt İşlemi İçin

```http
  POST /file/{customerId}
```

````
{
    "fileName":"OrnekDosya",
    "fileDescription":"Dosya Icerigi"
}
````

#### İstenen Dosya Kaydını Getirmek

```http
  GET /file/{fileId}
```

````
{
        "id": 8,
        "fileName": "HamdiBelgesi",
        "fileDescription": "Hamdi ve ailesinin hayatı",
        "customer": {
            "id": 2,
            "firstName": "Hamdi",
            "lastName": "Zıpkın",
            "identificationNumber": "84845622",
            "companyName": "Nosad",
            "phoneNumber": "555646887425",
            "email": "hamdizipkin@gmail.com",
            "address": "Antalya"
        }
    }
````
