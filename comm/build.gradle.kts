dependencies {
    // protocols & network
    api("com.nukkitx.protocol:bedrock-v486:2.9.5-SNAPSHOT")
    // internal deps
    api(project(":game"))
    api(project(":threading"))
    implementation("com.github.bedrockecs:common")
}
