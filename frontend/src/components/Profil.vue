<template>
	<div>
		<h1 class="card_title">Profil</h1>
		<p class="card_subtitle">Hello {{ utilisateur.username }} !</p>
	</div>
</template>

<script>
export default {
	name: "Profil",
	components: {},
	props: ["utilisateur"],
	methods: {
		followUser: function () {
			this.$store.dispatch("followUser", {
				username: this.$route.params.username,
			});
		},
		unfollowUser: function () {
			this.$store.dispatch("unfollowUser", {
				username: this.$route.params.username,
			});
		},
	},
};
</script>

<style>
</style>
