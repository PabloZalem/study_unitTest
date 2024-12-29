package builder;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {
    private Usuario usuario;

    public UsuarioBuilder(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioBuilder() {
    }

    public static UsuarioBuilder usuarioBuilder() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Usuario 1");
        return builder;
    }

    public Usuario agora() {
        return usuario;
    }
}
