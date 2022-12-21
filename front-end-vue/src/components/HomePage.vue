<template>
  <div>

    <h1>ciao</h1>
    <input type="text" placeholder="cerca" v-model="new_search">
                <button @click="search()" class="btn btn-primary mt-1">Cerca</button>
    <div class="d-flex justify-content-center">
        <div class="card" style="width: 18rem;"
            v-for="photo in photos"
            :key="photo.id"
        >
            <img :src="photo.url" class="card-img-top" >
            <div class="card-body">
                
                <input type="text" placeholder="Scrivi un commento" v-model="new_commento">
                <button @click="createComment(photo.id)" class="btn btn-primary mt-1">commenta</button>

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
            new_commento: "",
            new_search: ""
         };
    },

    methods: {
        createComment(id){
            console.log(this.new_commento, id)
            
            axios.post(API_URL + '/commento/create/' + id + '/' + this.new_commento)
            .then(res => {
        
            const commento = res.data;

            if (!commento) return;

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
                this.new_search="";
            }
        }
    },

    mounted() {
        this.getAllPhotos();
    }

}
</script>

<style>

</style>