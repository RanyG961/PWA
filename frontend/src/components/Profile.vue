<template>
	<div class="card">
		<h1 class="card_title">Profil</h1>
		<p class="card_subtitle">Hello {{ user.username }} !</p>
		<img :src="user.profilePicture" />
		<div>
			<h2>Tweets</h2>
			<p v-for="(tweet, index) in user.tweets" :key="index">
				{{ tweet.content }} {{ tweet.createdTime }}
			</p>
		</div>
		<div class="form-row">
			<button class="button" @click="logout()">Déconnexion</button>
		</div>
		<div class="form-row">
			<p @click="goToTimeline()">Timeline</p>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
	name: "Profil",
	computed: {
		...mapState({
			user: "user",
		}),
	},
	methods: {
		logout: function () {
			this.$store.commit("logout");
			this.$router.push("/");
		},
		goToTimeline: function () {
			this.$router.push("/Timeline");
		},
	},
};
</script>
