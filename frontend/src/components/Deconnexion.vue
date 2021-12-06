<template>
	<div class="form-row">
		<button class="button" @click="logout()">Déconnexion</button>
	</div>
</template>

<script>
export default {
	methods: {
		logout: function () {
			this.$store.commit("logout");
			this.$router.push("/");
		},
	},
};
</script>

<style>
</style>
