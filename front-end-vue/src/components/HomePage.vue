<template>
  <div class="father-container">
    <!-- accesso -->
    <div :class="{ 'main-body-container': fotoDetails, '': NoFotoDetails }">
        <div class="text-end">
            <a class="btn btn-warning m-3 " href="http://localhost:8080/foto">accedi</a>
        </div>
        <!-- ricerca / filtraggio -->
        <div class="d-flex justify-content-center">
            <div class="input-group w-50">
                <span class="input-group-text justify-content-center w-25">titolo / tag</span>
                <input type="text" class="form-control w-50" placeholder="cerca" v-model="new_search">  
            </div>
            <button @click="search()" class="btn btn-warning button-ricerca mx-2">Cerca</button>
        </div>
        <div class="d-flex flex-wrap mt-5 w-75 mx-auto">
            <div 
                @click="showFotoDetails(photo.id)"
                class="card card-style mx-4 my-3"
                v-for="photo in photos"
                :key="photo.id"
                :class="photo.visibile ? 'd-block' : 'd-none'"
            >
                <div class="img-container">
                    <img :src="photo.url" class="card-image" >
                </div>
                <div class="card-body">
                    <p class="card-text">{{photo.titolo}}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="box-father" :class="{ 'd-block': fotoDetails, 'd-none': NoFotoDetails }">
        <div class="box" >
            <div class="d-flex h-100">
                <div class="left-box h-100">
                    <img :src="focus.url" class="img-box" >
                </div>
                <div class="d-flex flex-column w-50 text-white position-relative">
                        <h1>{{ focus.titolo }}</h1>
                        <i class="fa-solid fa-x exit"  @click="colseFocus()"></i>
                        <div class="d-flex justify-content-around flex-wrap m-2">
                            <div v-for="categoria in focus.categorie" :key="categoria.id" class="p-2 bg-success  rounded-3 " >
                                {{ categoria.nome }}
                            </div>
                        </div>
                    <ul class="list-group scroll">
                        <li href="#" class="list-group-item disabled" aria-current="true">
                            commenti
                        </li>
                        <li href="#" class="list-group-item" v-for="commento in focus.commenti"
                            :key="commento.id">{{ commento.commento }}</li>
                    </ul>
                    <div class="input-group w-75 mx-auto mt-2 p-1">
                        <input type="text" placeholder="Scrivi un commento" class="form-control" v-model="new_commento">
                        <button @click="createComment(focus.id)" class="btn btn-outline-secondary" type="button" >commenta</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';
const API_URL = "http://localhost:8080/api/1";
export default {
    name: 'HomePage',

    data() {
         return {
            photos: [],
            new_commento : "",
            new_search : "",
            fotoDetails : false,
            NoFotoDetails : true,
            focus : {}
         };
    },

    methods: {
        createComment(id){
            axios.post(API_URL + '/commento/create/' + id + '/' + this.new_commento)
            .then(res => {
        
            const commento = res.data;

            if (!commento) return;
            this.focus.commenti.unshift(commento)
            this.getAllPhotos();
            });
            this.new_commento="";
        },

        search(){
            this.getAllPhotos();
        },

        getAllPhotos(){
            if (this.new_search == "") {
                axios.get(API_URL + '/foto/all')
                .then(res => {
                
                    const photos = res.data;
                    if (photos == null) return;
                    this.photos = photos;
                });
                
            }
            
            else{
                axios.get(API_URL + '/foto/all/' + this.new_search)
                    .then(res => {
                
                    const photos = res.data;
                    if (photos == null) return;
                    this.photos = photos;
                });
            }
           
        },
    
        showFotoDetails(id){
            this.fotoDetails = true;
            this.NoFotoDetails = false;
            for(let i = 0; i < this.photos.length; i++) {

                const photo = this.photos[i];

                if(photo.id == id) {
                    this.focus = photo;
                }
            }
        },

        colseFocus(){
            this.fotoDetails = false;
            this.NoFotoDetails = true;
            this.focus = {}
            this.new_commento = "";
        },
    },

    mounted() {
        this.getAllPhotos();
    }

}
</script>

<style lang="scss" scoped>
.img-container{
    width: 100%;
    height: 90%;
}
.card-image{
    height: 100%;
    width: 100%;
    object-fit: cover;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
    &:hover{
        cursor: pointer;
    }
}
.card-style{
    width: calc(100% / 5);
    height: 500px;
    border-width: 0;
    border-radius: 17px;
    &:hover{
        // transition:ease-in-out .4s;
        transition-duration: .4s;
        transform: scale(1.05);
    }
}
.father-container{
    position: relative;
}
.box{
    width: 75%;
    height: 500px;
    background-color: rgb(31 31 31);
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    transition-duration: 1s;
}
.left-box{
    width: 70%;
    height: 100%;
}
.img-box{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.scroll{
    height: 70%;
    overflow-y: auto;
}
.box-father{
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    z-index: 124;
    background: rgb(12 40 12 / 40%);
}
.main-body-container{
    filter: blur(8px);
}
.exit{
    position: absolute;
    left: 105%;
    padding: 10px;
    background-color: rgb(0, 172, 14);
    border-radius: 21px;
    transition-duration: .2s;
    &:hover{
        cursor: pointer;
        background-color: rgb(255, 37, 37);
    }
}
</style>