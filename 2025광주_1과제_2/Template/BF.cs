using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.Template
{
    public partial class BF : Form
    {
        public BF()
        {
            InitializeComponent();
        }

        private void BF_Load(object sender, EventArgs e)
        {

        }
        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);
            ActiveControl = null;
        }

        public static void msgInfo(string msg)
        {
            MessageBox.Show(msg,"정보",MessageBoxButtons.OK,MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            MessageBox.Show(msg,"경고",MessageBoxButtons.OK,MessageBoxIcon.Error);
        }
        public static Image GetImage(string path)
        {
            return Image.FromFile("./datafiles/"+path);
        }
        public virtual void updateForm()
        {
                
        }

        public void showPage(Form next)
        {
            Hide();
            next.ShowDialog();
            next.Dispose();
            updateForm();
            Show();
        }

        public void showPage(string title)
        {
            Stack<BF> stack = new Stack<BF>(Application.OpenForms.Cast<BF>());
            while (stack.Count>0)
            {
                var form = stack.Pop();
                if(form.Text== title)
                {
                    break;
                }
                else
                {
                    form.Close();
                }
            }
        }
    }

}
