<template>
	<div class="card">
		<h1 class="card_title">Espace Perso</h1>
		<p class="card_subtitle">Voilà donc qui je suis</p>
		<div class="form-row">
			<button class="button">Déconnexion</button>
		</div>
	</div>
</template>

<script>
export default {
	name: "Profile",
	mounted: function () {
		console.log(this.$store.state.user);
		if (this.$store.state.user.username == "") {
			this.$router.push("/");
			return;
		}

		this.$store.dispatch("getUserInfos");
	},
};
</script>

<style>
</style>
