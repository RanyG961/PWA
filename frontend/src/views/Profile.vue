<template>
	<div>
		<Navigation />
		<div class="card">
			<Profil :utilisateur="utilisateur"></Profil>
			<Tweet
				v-for="tweet in utilisateur.tweets"
				:key="tweet.id"
				:tweets="tweet"
			/>
			<!-- <div class="form-row">
				<button classe="timeline_link" @click="goToHome()">
					Timeline
				</button>
			</div> -->
			<Deconnexion />
		</div>
		<AppFooter />
	</div>
</template>
<script>
import Navigation from "../components/Navigation.vue";
import { mapState } from "vuex";
import Profil from "@/components/Profil.vue";
import Tweet from "@/components/Tweet.vue";
import AppFooter from "@/components/AppFooter.vue";
import Deconnexion from "@/components/Deconnexion.vue";

export default {
	name: "Profile",
	components: {
		Navigation,
		Profil,
		Tweet,
		AppFooter,
		Deconnexion,
	},
	mounted: function () {
		// console.log(this.$store.state.user);
		if (this.$store.state.user.username == "") {
			this.$router.push("/");
			return;
		}

		this.$store.dispatch("getUserInfos");
	},
	updated: function () {
		this.$store.dispatch("getUserInfos");
	},
	computed: {
		...mapState({
			utilisateur: "userInfos",
		}),
	},
	methods: {
		goToHome: function () {
			this.$router.push("/Home");
		},
	},
};
</script>

<style>
.timeline_link {
	text-align: center;
	width: 10em;
	height: 40px;
	padding: 12px 20px;
	box-sizing: border-box;
	border: 1px solid rgb(19, 24, 66);
	border-radius: 4px;
	background-color: #2196f3;
	font-size: 16px;
}
</style>
