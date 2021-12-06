<template>
	<div class="container">

		<div class="animation">
			<h1 id="welcome">Welcome to Wintter</h1>
		</div>

		<div class="logo">
				<img src="../assets/logo.png" div="logo_wintter" alt="logo_wintter">
			</div>

		<div class="card">
			
			<h1 class="card_title" v-if="mode == 'login'">Connexion</h1>
			<h1 class="card_title" v-else>Inscription</h1>

			<p class="card_subtitle" v-if="mode == 'login'">
				Tu n'as pas encore de compte ?
				<span class="card_action" @click="switchToCreateAccount()">
					Créer un compte</span
				>
			</p>

			<p class="card_subtitle" v-else>
				Tu as déjà un compte
				<span class="card_action" @click="switchToLogin()">
					Se connecter</span
				>
			</p>
			<div class="form-row" v-if="mode != 'login'">
				<input
					v-model="lastName"
					class="form-row_input"
					type="text"
					name="lastName"
					id="lastName"
					placeholder="Nom"
					required
				/>
				<input
					v-model="firstName"
					class="form-row_input"
					type="text"
					name="firstName"
					id="firstName"
					placeholder="Prénom"
					required
				/>
			</div>
			<div class="form-row">
				<input
					v-model="email"
					v-if="mode != 'login'"
					class="form-row_input"
					type="email"
					name="email"
					id="email"
					placeholder="Adresse email"
					required
				/>
				<input
					v-model="username"
					class="form-row_input"
					type="text"
					name="username"
					id="username"
					placeholder="Nom d'utilisateur"
					required
				/>
			</div>
			<div class="form-row">
				<input
					v-model="password"
					class="form-row_input"
					type="password"
					name="password"
					id="password"
					placeholder="Mot de passe"
					required
				/>
				<input
					v-if="mode != 'login'"
					v-model="passwordConfirm"
					class="form-row_input"
					type="password"
					name="passwordConfirm"
					id="passwordConfirm"
					placeholder="Confirmer le mot de passe"
					required
				/>
			</div>
			<div class="form-row" v-if="mode != 'login'">
				<input
					v-model="birthDate"
					class="form-row_input"
					type="date"
					name="date"
					id="date"
				/>
			</div>
			<div class="form-row" v-if="mode == 'login' && status == 'error_login'">
				Veuillez vérifier les informations saisies.
			</div>
			<div class="form-row">
				<button
					@click="login()"
					class="button"
					:class="{ 'button--disabled': !validatedFields }"
					v-if="mode == 'login'"
				>
					<span v-if="status == 'loading'"> Connexion en cours</span>
					<span v-else> Connexion </span>
				</button>
				<button
					@click="createAccount()"
					class="button"
					:class="{ 'button--disabled': !validatedFields }"
					v-else
				>
					<span v-if="status == 'loading'">Création en cours...</span>
					<span v-else>Créer mon compte</span>
				</button>
			</div>
		</div>
	</div>
	
</template>

<script>
import { mapState } from "vuex";

export default {
	name: "Login",
	data: function () {
		return {
			mode: "login",
			lastName: "",
			firstName: "",
			email: "",
			username: "",
			password: "",
			passwordConfirm: "",
			birthDate: "",
		};
	},
	computed: {
		validatedFields: function () {
			if (this.mode != "login") {
				if (
					this.lastName != "" &&
					this.firstName != "" &&
					this.email != "" &&
					this.username != "" &&
					this.password != "" &&
					this.passwordConfirm != "" &&
					this.birthDate != ""
				) {
					if (this.password === this.passwordConfirm) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if (this.username != "" && this.password != "") {
					return true;
				} else {
					return false;
				}
			}
		},
		...mapState(["status"]),
	},
	mounted: function () {
		if (this.$store.state.user.username != "") {
			this.$router.push("/Profile");
			return;
		}
	},
	methods: {
		switchToCreateAccount: function () {
			this.mode = "create";
		},
		switchToLogin: function () {
			this.mode = "login";
		},
		createAccount: function () {
			const self = this;
			this.$store
				.dispatch("createAccount", {
					lastName: this.lastName,
					firstName: this.firstName,
					email: this.email,
					username: this.username,
					password: this.password,
					// passwordConfirm: this.
					birthDate: this.birthDate,
				})
				.then(
					function () {
						self.login();
					},
					function (error) {
						console.log(error);
					}
				);
		},
		login: function () {
			const self = this;
			this.$store
				.dispatch("login", {
					username: this.username,
					password: this.password,
				})
				.then(
					function () {
						self.$router.push("profile");
					},
					function (error) {
						console.log(error);
					}
				);
		},
	},
};
</script>

<style>

.container{
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-template-rows: 50vh 50vh;
	grid-template-areas: 
		"animation logo"
		"animation forms"
}

.animation{
	grid-area: animation;
	display: flex;
	flex-direction: column;
	text-align: center;
}

#welcome{
	font-family: gotFont;
	font-size: 5em;
	line-height: 2em;
	word-spacing: 1rem;
	margin-top: 20%;
}

.logo{
	display: flex;
	justify-content: center;
	align-items: center;
}

#logo_wintter{
	width: 50%;
}

.card {
	grid-area: forms;
	max-width: 100%;
	width: 80%;
	height: 60%;
	max-height: 100%;
	background: white;
	border-radius: 1.5em;
	padding: 2em;
}

.card_title {
	text-align: center;
	font-weight: 800;
}

.card_subtitle {
	text-align: center;
	color: #666;
	font-weight: 500;
}

.card_action {
	color: #2196f3;
	text-decoration: underline;
}

.card_action:hover {
	cursor: pointer;
}

.form-row {
	display: flex;
	margin: 16px 0px;
	gap: 16px;
	flex-wrap: wrap;
}

.form-row_input {
	padding: 8px;
	border: none;
	border-radius: 8px;
	background: #f2f2f2;
	font-weight: 500;
	font-size: 16px;
	flex: 1;
	min-width: 100px;
	color: black;
}

.form-row_input::placeholder {
	color: #aaaaaa;
}

</style>
