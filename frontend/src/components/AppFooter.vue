<template>
    <div class="footer_container">
        <img src="../assets/logo.png" alt="footer_logo" class="footer_logo">
        <p class="wintter_infos">
            Wintter Corporation
        </p>
    </div>

</template>

<script>

export default {
	name: "appfooter",

};

</script>

<style>

.footer_container{
    width: 100%;
    height: 10%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: linear-gradient(62deg, #6b7077 0%, #a4acb4 100%);
    position: fixed;
    bottom: 0;
}

.footer_logo{
    height: 100%;
    object-fit: contain;
    margin-right: 2em;
} 

</style>

