package br.com.rjansen.desafios;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author raphaeljansen
 * 
 *         Classe reponsável por resolver o desafio sobre o Sistema Robo.
 *         Desafio proposto:
 * 
 *         Sistema Robô
 * 
 *         Suponha que você precisa implementar um robô controlado pelos
 *         seguintes comandos:
 *
 *         L - Virar 90 graus para a esquerda R - Virar 90 graus para a direita
 *         M - Mover um passo para frente T - Se tele-transportar para uma
 *         determinada célula
 *
 *         O robô anda em um plano cartesiano, em um espaço de tamanho
 *         especifico, com quatro direções Norte(N), Sul (S), Leste (E), Oeste
 *         (W). Ele não pode se mover ou tele transportar para fora desse
 *         espaço. O input do problema vem de um arquivo com o seguinte formato:
 *
 *         1a linha: o tamanho do espaço que o robô pode usar - X\<espaço\>Y 2a
 *         linha: localização inicial do robô e sua direção -
 *         X\<espaço\>Y\<espaço\>D 3a linha em diante: comandos do robô sem
 *         separação. A exceção é o comando de teletransporte, que deve ficar em
 *         sua própria linha com o seguinte formato - T\<espaço\>X\<espaço\>Y
 *
 *         Um exemplo (sem os comentários):
 *
 *         10 10 # o tamanho do espaço é 10 por 10 2 5 N # sua localização
 *         inicial é (2,5) e a sua direção é Norte LLRRMMMRLRMMM # um conjunto
 *         de comandos T 1 3 # o robô se tele transporta para o ponto (1,3)
 *         LLRRMMRMMRM # um outro conjunto de comandos
 *
 *         O resultado final deve ser dado pelo robô para indicar sua posição e
 *         para onde ele está apontando, por exemplo:
 *
 *         2 4 E # na posição 2 4, virado para Leste
 *
 *         Assuma que a célula imediatamente ao norte de (x, y) é (x, y + 1) e a
 *         leste é (x+1,y)
 *
 */
public class SistemaRobo {

	/**
	 * @author raphaeljansen
	 * 
	 *         Classe de erro pai. Nao pode ser instanciada, existe apenas para
	 *         abstrair as funcoes de formatacao da memsagem de erro.
	 * 
	 */
	public static abstract class ErroPai extends RuntimeException {
		private static final long serialVersionUID = 2228269031176988218L;

		public ErroPai(String message, Throwable cause,
				Object... parametrosMensagem) {
			super(String.format(message, parametrosMensagem), cause);
		}

		public ErroPai(String message, Object... parametrosMensagem) {
			super(String.format(message, parametrosMensagem));
		}

		public ErroPai(Throwable cause) {
			super(cause);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Classe para os erros de execucao do sistema.
	 * 
	 */
	public static class ExecucaoErro extends ErroPai {
		private static final long serialVersionUID = 3069564036531511623L;

		public ExecucaoErro(String message, Object... parametrosMensagem) {
			super(message, parametrosMensagem);
		}

		public ExecucaoErro(String message, Throwable cause,
				Object... parametrosMensagem) {
			super(message, cause, parametrosMensagem);
		}

		public ExecucaoErro(Throwable cause) {
			super(cause);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Ocorre quando uma coordenada fora do plano cartesiano e
	 *         encontrada.
	 * 
	 */
	public static class CoordenadaInvalidaErro extends ErroPai {
		private static final long serialVersionUID = -2782074373463610780L;

		public CoordenadaInvalidaErro(String message,
				Object... parametrosMensagem) {
			super(message, parametrosMensagem);
		}

		public CoordenadaInvalidaErro(String message, Throwable cause,
				Object... parametrosMensagem) {
			super(message, cause, parametrosMensagem);
		}

		public CoordenadaInvalidaErro(Throwable cause) {
			super(cause);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Ocorre quando um comando indevido e enviado para o sistema.
	 *
	 */
	public static class ComandoInvalidoErro extends ErroPai {
		private static final long serialVersionUID = -6266122395969583423L;

		public ComandoInvalidoErro(String message, Object... parametrosMensagem) {
			super(message, parametrosMensagem);
		}

		public ComandoInvalidoErro(String message, Throwable cause,
				Object... parametrosMensagem) {
			super(message, cause, parametrosMensagem);
		}

		public ComandoInvalidoErro(Throwable cause) {
			super(cause);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Representa um ponto no plano cartesiano x = indica a coluna no
	 *         plano cartesiano y = indica a linha no plano cartesiano
	 * 
	 */
	public static class Coordenada {
		private final Integer x;
		private final Integer y;

		public Coordenada(Integer x, Integer y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Integer getX() {
			return x;
		}

		public Integer getY() {
			return y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordenada other = (Coordenada) obj;
			if (x == null) {
				if (other.x != null)
					return false;
			} else if (!x.equals(other.x))
				return false;
			if (y == null) {
				if (other.y != null)
					return false;
			} else if (!y.equals(other.y))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.format("(x=%s, y=%s)", x, y);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Representa a orientacao no plano cartesiano
	 */
	public enum Direcao {
		NORTE("N"), SUL("S"), LESTE("E"), OESTE("W");

		private final String apelido;

		private Direcao(String apelido) {
			this.apelido = apelido;
		}

		public String getApelido() {
			return apelido;
		}

		public static Direcao valorPeloApelido(String apelido) {
			for (Direcao direcao : values()) {
				if (direcao.getApelido().equals(apelido)) {
					return direcao;
				}
			}
			throw new ExecucaoErro(
					"ImpossivelObterDirecaoPeloApelido: Apelido=[%s]", apelido);
		}
	}

	/**
	 * @author raphaeljansen
	 *
	 *         Representa uma posicao no plano cartesiano coordenada = ponto no
	 *         plano cartesiano (coluna, linha) direcao = orientacao da posicao,
	 *         indica para onde o objeto esta virado
	 */
	public static class Posicao {
		private final Coordenada coordenada;
		private final Direcao direcao;

		public Posicao(Coordenada coordenada, Direcao direcao) {
			super();
			this.coordenada = coordenada;
			this.direcao = direcao;
		}

		public Coordenada getCoordenada() {
			return coordenada;
		}

		public Direcao getDirecao() {
			return direcao;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((coordenada == null) ? 0 : coordenada.hashCode());
			result = prime * result
					+ ((direcao == null) ? 0 : direcao.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Posicao other = (Posicao) obj;
			if (coordenada == null) {
				if (other.coordenada != null)
					return false;
			} else if (!coordenada.equals(other.coordenada))
				return false;
			if (direcao != other.direcao)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.format("%s %s", coordenada, direcao.getApelido());
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Plano, espaco delimitador do objeto linhas = linhas do plano
	 *         cartesiano colunas = colunas do plano cartesiano
	 */
	public static class PlanoCartesiano {
		private final Integer linhas;
		private final Integer colunas;

		public PlanoCartesiano(Integer linhas, Integer colunas) {
			super();
			this.linhas = linhas;
			this.colunas = colunas;
		}

		public Integer getLinhas() {
			return linhas;
		}

		public Integer getColunas() {
			return colunas;
		}

		public boolean existeCoordenada(Coordenada coordenada) {
			return coordenada.getX() > 0 && coordenada.getY() > 0
					&& coordenada.getX() <= colunas
					&& coordenada.getY() <= linhas;
		}

		public void validaCoordenada(Coordenada coordenada) {
			if (!existeCoordenada(coordenada)) {
				throw new CoordenadaInvalidaErro(
						"CoordenadaForaLimite: Limite=%sx%s Coordenada=%s",
						linhas, colunas, coordenada);
			}
		}

		@Override
		public String toString() {
			return String.format("PlanoCartesiano [linhas=%s, colunas=%s]",
					linhas, colunas);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Representa a entidade robo, um obejto que pode andar pelo plano
	 *         cartesiano planoCartesiano = plano na qual o robo foi colocado
	 *         posicaoInicial = posicao aonde o robo foi colocado no plano
	 *         posicao = posicao atual de onde o robo esta no plano
	 */
	private static class Robo {
		private final PlanoCartesiano planoCartesiano;
		private final Posicao posicaoInicial;
		private Posicao posicao;

		public Robo(PlanoCartesiano planoCartesiano, Posicao posicaoInicial) {
			super();
			this.planoCartesiano = planoCartesiano;
			this.posicaoInicial = posicaoInicial;
			this.posicao = this.posicaoInicial;
		}

		public Posicao getPosicaoInicial() {
			return posicaoInicial;
		}

		public Posicao getPosicao() {
			return posicao;
		}

		public void virarEsquerda() {
			final Direcao direcaoAtual = posicao.getDirecao();
			final Direcao direcaoNova;
			switch (direcaoAtual) {
			case NORTE:
				direcaoNova = Direcao.OESTE;
				break;
			case SUL:
				direcaoNova = Direcao.LESTE;
				break;
			case LESTE:
				direcaoNova = Direcao.NORTE;
				break;
			case OESTE:
				direcaoNova = Direcao.SUL;
				break;
			default:
				throw new ExecucaoErro("DirecaoInvalida: Direcao=%s",
						direcaoAtual);
			}
			alterarPosicao(new Posicao(posicao.getCoordenada(), direcaoNova));
		}

		public void virarDireita() {
			final Direcao direcaoAtual = posicao.getDirecao();
			final Direcao direcaoNova;
			switch (direcaoAtual) {
			case NORTE:
				direcaoNova = Direcao.LESTE;
				break;
			case SUL:
				direcaoNova = Direcao.OESTE;
				break;
			case LESTE:
				direcaoNova = Direcao.SUL;
				break;
			case OESTE:
				direcaoNova = Direcao.NORTE;
				break;
			default:
				throw new ExecucaoErro("DirecaoInvalida: Direcao=%s",
						direcaoAtual);
			}
			alterarPosicao(new Posicao(posicao.getCoordenada(), direcaoNova));
		}

		public void mover() {
			final Direcao direcaoAtual = posicao.getDirecao();
			final Coordenada coordenadaAtual = posicao.getCoordenada();
			final Coordenada coordenadaNova;
			switch (direcaoAtual) {
			case NORTE:
				coordenadaNova = new Coordenada(coordenadaAtual.getX(),
						coordenadaAtual.getY() + 1);
				break;
			case SUL:
				coordenadaNova = new Coordenada(coordenadaAtual.getX(),
						coordenadaAtual.getY() - 1);
				break;
			case LESTE:
				coordenadaNova = new Coordenada(coordenadaAtual.getX() + 1,
						coordenadaAtual.getY());
				break;
			case OESTE:
				coordenadaNova = new Coordenada(coordenadaAtual.getX() - 1,
						coordenadaAtual.getY());
				break;
			default:
				throw new ExecucaoErro("DirecaoInvalida: Direcao=%s",
						direcaoAtual);
			}
			alterarPosicao(new Posicao(coordenadaNova, direcaoAtual));
		}

		public void teletransportar(Integer x, Integer y) {
			alterarPosicao(new Posicao(new Coordenada(x, y),
					posicao.getDirecao()));
		}

		private void alterarPosicao(final Posicao posicaoNova) {
			planoCartesiano.validaCoordenada(posicaoNova.getCoordenada());
			this.posicao = posicaoNova;
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Tipo de comando que pode ser enviado ao robo
	 */
	public enum TipoComando {
		VIRAR_ESQUERDA("L"), VIRAR_DIREITA("R"), MOVER("M"), TELETRANSPORTAR(
				"T");

		private final String apelido;

		private TipoComando(String apelido) {
			this.apelido = apelido;
		}

		public String getApelido() {
			return apelido;
		}

		public static TipoComando valorPeloApelido(String apelido) {
			for (TipoComando comando : values()) {
				if (comando.getApelido().equals(apelido)) {
					return comando;
				}
			}
			throw new ExecucaoErro(
					"ImpossivelObterComandoPeloApelido: Apelido=[%s]", apelido);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Comando enviado ao robo
	 */
	public static class Comando {
		private final TipoComando tipoComando;
		private final List<String> parametros;

		public Comando(final TipoComando tipoComando,
				final String... parametros) {
			this.tipoComando = tipoComando;
			this.parametros = Arrays.asList(parametros);
		}

		public TipoComando getTipoComando() {
			return tipoComando;
		}

		public List<String> getParametros() {
			return parametros;
		}

		public void executar(Robo robo) {
			switch (tipoComando) {
			case VIRAR_ESQUERDA:
				robo.virarEsquerda();
				break;
			case VIRAR_DIREITA:
				robo.virarDireita();
				break;
			case MOVER:
				robo.mover();
				break;
			case TELETRANSPORTAR:
				robo.teletransportar(Integer.valueOf(parametros.get(0)),
						Integer.valueOf(parametros.get(1)));
				break;
			default:
				throw new ExecucaoErro("TipoComandoInvalido: TipoComando=%s",
						tipoComando);
			}
		}

		@Override
		public String toString() {
			return String.format("Comando [tipoComando=%s, parametros=%s]",
					tipoComando, parametros);
		}
	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Analisa e prepara o plano cartesiano, os dados iniciais e os
	 *         comandos enviados ao sistema
	 */
	public static class InterpretadorComandos {
		private final Pattern TAMAMHO_ESPACO_PATTERN = Pattern
				.compile("(?<linhas>\\d+)[ ](?<colunas>\\d+)");
		private final Pattern POSICAO_PATTERN = Pattern
				.compile("(?<x>\\d+)[ ](?<y>\\d+)[ ](?<direcao>[NSEW])");
		private final Pattern COMANDOS_PATTERN = Pattern
				.compile("(?<comando>[LRMT])([ ](?<x>\\d+)[ ](?<y>\\d+))?");
		private final Charset defaultArquivoCharset = Charset.forName("UTF-8");

		private final String caminhoArquivoComandos;
		private List<String> comandos;

		public InterpretadorComandos(final String caminhoArquivoComandos) {
			this.caminhoArquivoComandos = caminhoArquivoComandos;
		}

		public void carregaComandos() throws Exception {
			comandos = Files.readAllLines(
					Paths.get(this.caminhoArquivoComandos),
					defaultArquivoCharset);
		}

		public PlanoCartesiano getPlanoCartesiano() {
			final String cmdPosicao = this.comandos.get(0);
			final Matcher matcher = TAMAMHO_ESPACO_PATTERN.matcher(cmdPosicao);
			if (matcher.find()) {
				final PlanoCartesiano planoCartesiano = new PlanoCartesiano(
						Integer.parseInt(matcher.group("linhas")),
						Integer.parseInt(matcher.group("colunas")));
				return planoCartesiano;
			} else {
				throw new ComandoInvalidoErro(
						"ComandoPlanoCartesianoInvalido: Comando_PlanoCartesiano=[%s]",
						cmdPosicao);
			}
		}

		public Posicao getPosicaoIncicial() {
			final String cmdPosicao = this.comandos.get(1);
			final Matcher matcher = POSICAO_PATTERN.matcher(cmdPosicao);
			if (matcher.find()) {
				final Posicao posicao = new Posicao(new Coordenada(
						Integer.parseInt(matcher.group("x")),
						Integer.parseInt(matcher.group("y"))),
						Direcao.valorPeloApelido(matcher.group("direcao")));
				return posicao;
			} else {
				throw new ComandoInvalidoErro(
						"ComandoPosicaoInicialInvalido: Comando_PosicaoInicial=[%s]",
						cmdPosicao);
			}
		}

		public List<Comando> getComandos() {
			final List<Comando> listaComandos = new ArrayList<>();
			final List<String> comandoRoboLista = this.comandos.subList(2,
					this.comandos.size());
			try {
				for (final String comandoRobo : comandoRoboLista) {
					final Matcher matcher = COMANDOS_PATTERN
							.matcher(comandoRobo);
					while (matcher.find()) {
						final TipoComando tipoComando = TipoComando
								.valorPeloApelido(matcher.group("comando"));
						final List<String> comandoParametros = new ArrayList<>();
						if (tipoComando == TipoComando.TELETRANSPORTAR) {
							final String x = matcher.group("x");
							final String y = matcher.group("y");
							Integer.parseInt(x);
							Integer.parseInt(y);
							comandoParametros.add(x);
							comandoParametros.add(y);
						}
						listaComandos.add(new Comando(tipoComando,
								comandoParametros
										.toArray(new String[comandoParametros
												.size()])));
					}
				}
			} catch (Exception e) {
				throw new ComandoInvalidoErro(e);
			}
			if (listaComandos.isEmpty()) {
				out.printf("ListaComandosVazia: Comandos_Robo=[%s]",
						comandoRoboLista);
			}
			return listaComandos;
		}

	}

	/**
	 * @author raphaeljansen
	 * 
	 *         Representa o resultado da execucao dos comandos enviados ao
	 *         sistema.
	 */
	public static class ResultadoSistemaRobo {
		private final Posicao posicaoInicial;
		private final Posicao posicaoFinal;

		public ResultadoSistemaRobo(Posicao posicaoInicial, Posicao posicaoFinal) {
			super();
			this.posicaoInicial = posicaoInicial;
			this.posicaoFinal = posicaoFinal;
		}

		public Posicao getPosicaoInicial() {
			return posicaoInicial;
		}

		public Posicao getPosicaoFinal() {
			return posicaoFinal;
		}

		@Override
		public String toString() {
			return String
					.format("ResultadoSistemaRobo [posicaoInicial=%s, posicaoFinal=%s]",
							posicaoInicial, posicaoFinal);
		}
	}

	private final InterpretadorComandos interpretadorComandos;

	public SistemaRobo(final InterpretadorComandos interpretadorComandos) {
		this.interpretadorComandos = interpretadorComandos;
	}

	/**
	 * Executa o sistema com o arquivo de comandos informado
	 * 
	 * @return ResultadoSistemaRobo - Resultado da execucao dos comandos
	 */
	public ResultadoSistemaRobo executar() {
		try {
			interpretadorComandos.carregaComandos();
			final PlanoCartesiano planoCartesiano = interpretadorComandos
					.getPlanoCartesiano();
			out.printf("PlanoCartesiano: %s\n", planoCartesiano);
			final Posicao posicaoInicial = interpretadorComandos
					.getPosicaoIncicial();
			out.printf("PosicaoInicial: %s\n", posicaoInicial);
			final Robo robo = new Robo(planoCartesiano, posicaoInicial);
			final List<Comando> comandoRoboLista = interpretadorComandos
					.getComandos();
			for (final Comando comandoRobo : comandoRoboLista) {
				comandoRobo.executar(robo);
				out.printf("Executado: %s PosicaoRobo=%s\n", comandoRobo,
						robo.getPosicao());
			}
			return new ResultadoSistemaRobo(robo.getPosicaoInicial(),
					robo.getPosicao());
		} catch (ErroPai erroPai) {
			throw erroPai;
		} catch (Exception e) {
			throw new ExecucaoErro(e);
		}
	}

	/**
	 * Imprime um exemplo de chamada da classe
	 */
	private static void printUsage() {
		out.println("Use: java br.com.rjansen.desafios.SistemaRobo <arquivo_comandos>\n<arquivo_comandos>=/caminho_arquivo/comandos\n");
	}

	public static void main(String[] args) {
		final long startTime = currentTimeMillis();
		final String caminhoArquivoComandos;
		if (args.length < 1 || args[0] == null) {
			printUsage();
			return;
		} else {
			caminhoArquivoComandos = args[0].trim();
		}
		out.println("****** Sistema Robo - Maquina que nterpreta e executa comandos ******");
		out.println("****** Aguarde ... Analisando os comandos ******");
		SistemaRobo sistemaRobo = new SistemaRobo(new InterpretadorComandos(
				caminhoArquivoComandos));
		final ResultadoSistemaRobo resultadoSistemaRobo = sistemaRobo
				.executar();
		out.printf(
				"Posicao Final Robo: \n\tPosicao Inicial=%s \n\tPosicao Final=%s \n",
				resultadoSistemaRobo.getPosicaoInicial(),
				resultadoSistemaRobo.getPosicaoFinal());
		final long endTime = currentTimeMillis();
		out.printf(
				"****** Tempo Execucao Subcadeia Soma Maxima: %dms ******\n",
				endTime - startTime);
	}
}
