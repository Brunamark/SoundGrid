package com.soundgrid.api.infraestructure.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soundgrid.api.application.port.input.MusicUseCase;
import com.soundgrid.api.application.port.output.MusicRepository;
import com.soundgrid.api.application.service.MusicServiceImpl;
import com.soundgrid.api.domain.model.Music;
import com.soundgrid.api.domain.types.Genre;

@Configuration
public class BeanConfiguration {

    @Bean
    public MusicUseCase musicUseCase(MusicRepository repository) {
        return new MusicServiceImpl(repository);
    }

    @Bean
    public CommandLineRunner run(MusicUseCase musicService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n🎵 MENU 🎵");
                System.out.println("1. Listar músicas");
                System.out.println("2. Buscar música por ID");
                System.out.println("3. Cadastrar nova música");
                System.out.println("4. Atualizar música");
                System.out.println("5. Deletar música");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        listarMusicas(musicService);
                        break;
                    case "2":
                        encontrarMusicaPeloId(musicService, scanner);
                        break;
                    case "3":
                        cadastrarMusica(musicService, scanner);
                        break;
                    case "4":
                        atualizarMusica(musicService, scanner);
                        break;
                    case "5":
                        deletarMusica(musicService, scanner);
                        break;
                    case "0":
                        System.out.println("👋 Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }
            }
        };
    }

    private static void listarMusicas(MusicUseCase musicService) {
        Map<Genre, List<Music>> genreMusic = musicService.getAllMusic();

        if (genreMusic.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (Map.Entry<Genre, List<Music>> entry : genreMusic.entrySet()) {
            Genre genre = entry.getKey();
            List<Music> musics = entry.getValue();

            System.out.println("\n🎶 Gênero: " + genre);
            System.out.println("------------------------");

            if (musics.isEmpty()) {
                System.out.println("Nenhuma música disponível neste gênero.");
            } else {
                for (Music music : musics) {
                    System.out.println(music.getId() + " - " + music.getTitle() + " - " + music.getArtist());
                }
            }
        }
    }

    private static void encontrarMusicaPeloId(MusicUseCase musicService, Scanner scanner) {
        System.out.print("Digite o ID da música: ");
        Long id = Long.parseLong(scanner.nextLine());

        Music music = musicService.getMusicById(id);
        if (music != null) {
            System.out.println("✅ Música encontrada: " + music.getTitle() + " - " + music.getArtist());
        } else {
            System.out.println("❌ Música não encontrada.");
        }
    }

    private static void cadastrarMusica(MusicUseCase musicService, Scanner scanner) {
        System.out.println("🎵 Cadastro de Música 🎵");

        String title;
        do {
            System.out.print("Título: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("❌ O título não pode estar vazio.");
            }
        } while (title.isEmpty());

        String artist;
        do {
            System.out.print("Artista: ");
            artist = scanner.nextLine().trim();
            if (artist.isEmpty()) {
                System.out.println("❌ O artista não pode estar vazio.");
            }
        } while (artist.isEmpty());

        System.out.print("Álbum (pressione Enter se não houver): ");
        String album = scanner.nextLine().trim();

        Genre genre = null;
        while (genre == null) {
            System.out.println("Escolha um gênero entre: " + Arrays.toString(Genre.values()));
            System.out.print("Gênero: ");
            String genreInput = scanner.nextLine().toUpperCase().trim();
            try {
                genre = Genre.valueOf(genreInput);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Gênero inválido! Escolha um dos seguintes: " + Arrays.toString(Genre.values()));
            }
        }

        LocalDate releaseDate = null;
        while (releaseDate == null) {
            System.out.print("Data de lançamento (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine().trim();
            try {
                releaseDate = LocalDate.parse(dateInput);
            } catch (Exception e) {
                System.out.println("❌ Data inválida! Use o formato correto: YYYY-MM-DD.");
            }
        }

        int duration = -1;
        while (duration <= 0) {
            System.out.print("Duração (segundos): ");
            String durationInput = scanner.nextLine().trim();
            try {
                duration = Integer.parseInt(durationInput);
                if (duration <= 0) {
                    System.out.println("❌ A duração deve ser um número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida! Digite um número inteiro positivo.");
            }
        }

        String filePath;
        do {
            System.out.print("Caminho do arquivo: ");
            filePath = scanner.nextLine().trim();
            if (filePath.isEmpty()) {
                System.out.println("❌ O caminho do arquivo não pode estar vazio.");
            }
        } while (filePath.isEmpty());

        Music novaMusica = new Music(
                null, title, artist, album, genre, releaseDate, duration, filePath, LocalDateTime.now(), null);

        musicService.createMusic(novaMusica);
        System.out.println("\n✅ Música cadastrada com sucesso!");
    }

    private static void atualizarMusica(MusicUseCase musicService, Scanner scanner) {
        System.out.print("Digite o ID da música para atualizar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Music musicaExistente = musicService.getMusicById(id);
        if (musicaExistente == null) {
            System.out.println("❌ Música com ID " + id + " não encontrada.");
            return;
        }

        System.out.print("Novo título (Enter para manter: " + musicaExistente.getTitle() + "): ");
        String title = scanner.nextLine();
        if (title.isEmpty())
            title = musicaExistente.getTitle();

        System.out.print("Novo artista (Enter para manter: " + musicaExistente.getArtist() + "): ");
        String artist = scanner.nextLine();
        if (artist.isEmpty())
            artist = musicaExistente.getArtist();

        System.out.print("Novo álbum (Enter para manter: " + musicaExistente.getAlbum() + "): ");
        String album = scanner.nextLine();
        if (album.isEmpty())
            album = musicaExistente.getAlbum();

        System.out.println("Escolha um novo gênero: " + Arrays.toString(Genre.values()));
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Nova data de lançamento (YYYY-MM-DD) ou Enter para manter: "
                + musicaExistente.getReleaseDate() + ": ");
        String dateInput = scanner.nextLine();
        LocalDate releaseDate = dateInput.isEmpty() ? musicaExistente.getReleaseDate() : LocalDate.parse(dateInput);

        System.out.print("Nova duração (segundos) ou Enter para manter: " + musicaExistente.getDuration() + ": ");
        String durationInput = scanner.nextLine();
        int duration = durationInput.isEmpty() ? musicaExistente.getDuration() : Integer.parseInt(durationInput);

        System.out.print("Novo caminho do arquivo (Enter para manter: " + musicaExistente.getFilePath() + "): ");
        String filePath = scanner.nextLine();
        if (filePath.isEmpty())
            filePath = musicaExistente.getFilePath();

        Music musicaAtualizada = new Music(id, title, artist, album, genre, releaseDate, duration, filePath,
                musicaExistente.getCreatedAt(), LocalDateTime.now());

        musicService.updateMusic(id, musicaAtualizada);
        System.out.println("\n✅ Música atualizada com sucesso!");
    }

    private static void deletarMusica(MusicUseCase musicService, Scanner scanner) {
        System.out.print("Digite o ID da música para deletar: ");
        Long id = Long.parseLong(scanner.nextLine());

        if (musicService.getMusicById(id) == null) {
            System.out.println("❌ Música com ID " + id + " não encontrada.");
            return;
        }

        musicService.deleteMusic(id);
        System.out.println("✅ Música deletada com sucesso!");
    }
}
