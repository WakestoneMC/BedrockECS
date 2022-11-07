<div style="text-align: center">
  <h1>BedrockECS</h1>

  <p>An experimental, FP&ECS-inspired MC:BE Server Software</p>

  <p>
    <a href="https://www.repostatus.org/#concept">
      <img src="https://www.repostatus.org/badges/latest/concept.svg" alt="Project Status: Concept – Minimal or no implementation has been done yet">
    </a>
    <a href="https://codeclimate.com/github/WakestoneMC/BedrockECS/maintainability">
      <img src="https://api.codeclimate.com/v1/badges/0e49485e5101aaa6da50/maintainability"/>
    </a>
    <a href="https://codeclimate.com/github/WakestoneMC/BedrockECS/test_coverage">
      <img src="https://api.codeclimate.com/v1/badges/0e49485e5101aaa6da50/test_coverage"/>
    </a>
  </p>
</div>

## :compass: Project Status & Roadmap

**Early Alpha Software, not suitable for production**

Potential Update on Feb/2023:
* further stabilize & document the core framework
* maybe implement creative mode gameplay

This project will then be put in hibernation till (hopefully) 2024/2025. 

Please refer to [this blogpost series(chinese)](https://0xffff.one/d/1369-guan-yu-wo-xie-le-ge-minecraft-ji) for motivations and design overview

Also, refer to [conventionalcommit.json](./conventionalcommit.json) for the code organization of the project

Forks are welcomed!

## :toolbox: Getting Started

Required Java/JDK Version: >= 17

### Run Server

Download the jar from the release page and run it
```bash
java -jar *name-of-the-jar.jar*
```

### Development

We use gradle as our build system, therefore all standard tasks should just work.

packaging:
```bash
gradle server:bootJar
```

publishing:
```bash
gradle publish
```

## :space_invader: Tech Stack

Kotlin + Spring Boot

Yes we don't use Spring Boot for game servers, but we use spring for DI and spring boot is so convenient!

## :gem: Acknowledgements

- [Awesome README template](https://github.com/Louis3797/awesome-readme-template)
- [Cloudburst Protocol Library](https://github.com/CloudburstMC/Protocol)
- [PowerNukkit](https://github.com/PowerNukkit/PowerNukkit)
