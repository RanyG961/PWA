<template>
    <div class="sidebar" v-if="afficher">
        <div class="menu">
            <button class="" @click="toggle_close()">Close &times;</button>
		</div>
        <a>
            <router-link to="/" class="lien">
                <span>Accueil</span>
            </router-link>
        </a>
        <a>    
            <router-link to="/profile" class="lien">
                <span>Profile</span>
            </router-link>
        </a>
        <a>    
            <router-link to="/conversation" class="lien">
                <span>Conversations</span>
            </router-link>
        </a>
        <a>    
            <router-link to="/notification" class="lien">
                <span>Notifications</span>
            </router-link>
        </a>
    </div>
    
	<header class="navbar">
        <!-- <div class="logo">
            <img src="../assets/logo.png" alt="logo_wintter" id="logo_wintter">
		</div> -->
        <div v-if="!afficher" class="">
            <button id="open" class="" @click="toggle_open()">&#9776;</button>
        </div>
        <nav class="redirection">
          <router-link to="/" class="lien">
            <span>Accueil</span>
          </router-link>
          <router-link to="/profile" class="lien">
            <span>Profile</span>
          </router-link>
        </nav>
    </header> 
    
    <!-- <router-view>

	</router-view> -->


</template>

<script>

export default {
	name: "navigation",
    data: function(){
        return {
            afficher: false
        }
    },
    methods: {
        toggle_open(){
            this.afficher = !this.afficher
            document.querySelector(".main").style.marginLeft = "15%";
            document.querySelector(".sidebar").style.width = "15%";
            document.querySelector(".sidebar").style.display = "block";
        },
        toggle_close(){
            this.afficher = !this.afficher
            document.querySelector(".main").style.marginLeft = "0%";
            document.querySelector(".sidebar").style.display = "none";
        }
    }
};

</script>

<style>

header{
	display: flex;
	height: 7vh;
	width: 100%;
	background-image: linear-gradient(62deg, #b2bac9 0%, #dfe6ee 100%);
    font-family: gotFont;
}

.bouton_toggle{
	margin-left: 2em;
	align-self: center;
}

.redirection{
	margin-left: auto;
    margin-right: auto;
	align-self: center;
}

.sidebar {
    color: white;
    background-color: rgb(9, 90, 165);
    float: left;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    bottom: 0;
    padding: 0.5em;
    transition: 0.3 ease;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-family: gotFont;
}


</style>