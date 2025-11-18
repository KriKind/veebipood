import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import english from "../assets/united-kingdom.png"
import estonian from "../assets/estonia.png"
import '../index.css'
import { useContext } from 'react';
import { CartSumContext } from '../context/CartSumContext';

function Menu() {
  const { t, i18n } = useTranslation();
  const {cartSum} = useContext(CartSumContext)

  function updateLanguage(newLang: string) {
    i18n.changeLanguage(newLang)
    localStorage.setItem("language", newLang)
  }
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">Veebipood</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/ostukorv">{t("menu.ostukorv")}</Nav.Link>
            <Nav.Link as={Link} to="/lisa-toode">{t("menu.lisa-toode")}</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link as={Link} to="/login">{t("menu.login")}</Nav.Link>
            <Nav.Link as={Link} to="/singnup">{t("menu.signup")}</Nav.Link>
          </Nav>
          <span>{cartSum.toFixed(2)} EUR</span>
          <img src={english} className='icon' onClick={() => updateLanguage("en")} alt="" />
          <img src={estonian} className='icon' onClick={() => updateLanguage("et")} alt="" />
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Menu;